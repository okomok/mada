

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.para


import java.util.ArrayList
import stl.IntroSort.depthLimit
import stl.{ UnguardedPartition, Median }
import Functions.future


private[mada] object Sort {
    def apply[A](v: Vector[A], lt: Compare.Func[A], grainSize: Int): Vector[A] = {
        Assert(!IsParallel(v))
        impl(v, v.start, v.end, lt, grainSize)
        v
    }

    def impl[A](v: Vector[A], first: Int, last: Int, lt: Compare.Func[A], grainSize: Int): Unit = {
        if (first != last) {
            val rs = new ArrayList[Vector[A]]
            loop(v, first, last, depthLimit(first, last), lt, grainSize, rs)
            Vector.fromJclList(rs).parallel(1).each{ r => r.sortBy(lt) }
        }
    }

    // See: stl.IntroSort
    def loop[A](* : Vector[A], __first: Int, last: Int, depth_limit: Int, __comp: Compare.Func[A], grainSize: Int, rs: ArrayList[Vector[A]]): Unit = {
        var __last = last
        var __depth_limit = depth_limit

        while (__last - __first > grainSize) {
            if (__depth_limit == 0) {
                rs.add(*(__first, __last))
                return
            }
            __depth_limit -= 1
            val __cut = UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            loop(*, __cut, __last, __depth_limit, __comp, grainSize, rs)
            __last = __cut
        }

        rs.add(*(__first, __last))
    }
}
