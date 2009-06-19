

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private object ParallelCount {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Int = {
        util.assert(!IsParallel(_1))
        _1.parallel(_3).collect(_.count(_2)).reduce(_ + _)
    }
}
