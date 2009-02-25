

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


import java.util.ArrayList


private[mada] object SortWith {
    import stl.IntroSort.lg
    import stl.{ UnguardedPartition, Median }

    def apply[A](v: Vector[A], lt: Compare.Type[A], grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        partition(v, lt, grainSize).parallel(1).each{ f => f() }
        v.parallel(grainSize)
    }

    def partition[A](v: Vector[A], lt: Compare.Type[A], grainSize: Int): Vector[() => Unit] = {
        val fs = new ArrayList[() => Unit]
        loop(fs, grainSize, v, v.start, v.end, lg(v.size) * 2, lt)
        Vector.fromJclList(fs)
    }

    // See: stl.IntroSort
    def loop[A](fs: ArrayList[() => Unit], grainSize: Int, * : Vector[A], __first: Int, last: Int, depth_limit: Int, __comp: Compare.Type[A]): Unit = {
        var __last = last
        var __depth_limit = depth_limit

        while (__last - __first > grainSize) {
            if (__depth_limit == 0) {
                fs.add({ () => *(__first, __last).sortWith(__comp) })
                return
            }
            val __cut = UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            __depth_limit /= 2
            loop(fs, grainSize, *, __cut, __last, __depth_limit, __comp)
            __last = __cut
        }
        fs.add({ () => *(__first, __last).sortWith(__comp) })
    }
}
