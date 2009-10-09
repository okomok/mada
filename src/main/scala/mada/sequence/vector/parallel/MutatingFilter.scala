

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


private object ParallelMutatingFilter {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Vector[A] = {
        util.assert(!IsParallel(_1))
        _1.divide(_3).parallel(1).map(_.mutatingFilter(_2)).flatten.toVector
    }
}
