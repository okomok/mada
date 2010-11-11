

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


import java.util.ArrayList
import eval.Future
import stl.IntroSort.depthLimit
import stl.{UnguardedPartition, Median}


private
object ParallelSort {
    def apply[A](_1: Vector[A], _2: Ordering[A], _3: Int) {
        assert(!IsParallel(_1))
        impl(_1, _1.start, _1.end, _2, _3 * 2) // best grain size?
    }

    def impl[A](v: Vector[A], first: Int, last: Int, c: Ordering[A], grainSize: Int) {
        if (first != last) {
            val fs = new ArrayList[Future[Unit]]
            loop(v, first, last, depthLimit(first, last), c, grainSize, fs)
            JoinFutures(fs)
        }
    }

    // See: stl.IntroSort
    def loop[A](* : Vector[A], __first: Int, last: Int, depth_limit: Int, __comp: Ordering[A], grainSize: Int, fs: ArrayList[Future[Unit]]) {
        var __last = last
        var __depth_limit = depth_limit

        def go(f: Int, l: Int) = fs.add(Future{stl.Sort(*, f, l, __comp)}) // call stl directly for speed.

        while (__last - __first > grainSize) {
            if (__depth_limit == 0) {
                go(__first, __last)
                return
            }
            __depth_limit -= 1
            val __cut = UnguardedPartition(*, __first, __last, Median(*(__first), *(__first + (__last - __first)/2), *(__last - 1), __comp), __comp)
            loop(*, __cut, __last, __depth_limit, __comp, grainSize, fs)
            __last = __cut
        }

        go(__first, __last)
    }
}
