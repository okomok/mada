

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private object ParallelCount {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Int = {
        assert(!IsParallel(_1))
        _1.divide(_3).parallelBy(1).map(_.count(_2)).reduce(_ + _)
    }
}
