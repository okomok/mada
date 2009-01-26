

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import java.util.ArrayList


object SortWith {
    import stl.IntroSort.lg
    import stl.{ UnguardedPartition, Median }

    def apply[A](v: Vector[A], lt: (A, A) => Boolean, grainSize: Int): Vector[A] = {
        Assert(!v.isParallel)
        partition(v, lt, grainSize).parallel(1).pareach({ f => f() })
        v.parallel(grainSize)
    }

    def partition[A](v: Vector[A], lt: (A, A) => Boolean, grainSize: Int): Vector[() => Unit] = {
        val fs = new ArrayList[() => Unit]
        val (x, i, j) = v.triple
        loop(fs, grainSize, x, i, j, lg(j - i) * 2, lt)
        Vector.jclListVector(fs)
    }

    // See: stl.IntroSort
    def loop[A](fs: ArrayList[() => Unit], grainSize: Int, * : Vector[A], __first: Int, last: Int, depth_limit: Int, __comp: (A, A) => Boolean): Unit = {
        var __last = last
        var __depth_limit = depth_limit

        while (__last - __first > grainSize) {
            if (__depth_limit == 0) {
                fs.add({ () => *.window(__first, __last).sortWith(__comp) })
                return
            }
            val __cut = UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            __depth_limit /= 2
            loop(fs, grainSize, *, __cut, __last, __depth_limit, __comp)
            __last = __cut
        }
        fs.add({ () => *.window(__first, __last).sortWith(__comp) })
    }
}
