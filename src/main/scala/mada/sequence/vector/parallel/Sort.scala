

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


import java.util.ArrayList
import stl.IntroSort.depthLimit
import stl.{UnguardedPartition, Median}


private object ParallelSort {
    def apply[A](_1: Vector[A], _2: Ordering[A], _3: Int): Vector[A] = {
        util.assert(!IsParallel(_1))
        impl(_1, _1.start, _1.end, _2, _3 * 2) // best grain size?
        _1
    }

    def impl[A](v: Vector[A], first: Int, last: Int, c: Ordering[A], grainSize: Int): Unit = {
        if (first != last) {
            val rs = new ArrayList[Vector[A]]
            loop(v, first, last, depthLimit(first, last), c, grainSize, rs)
            fromJList(rs).parallelBy(1).each(_.sort(c))
        }
    }

    // See: stl.IntroSort
    def loop[A](* : Vector[A], __first: Int, last: Int, depth_limit: Int, __comp: Ordering[A], grainSize: Int, rs: ArrayList[Vector[A]]): Unit = {
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
