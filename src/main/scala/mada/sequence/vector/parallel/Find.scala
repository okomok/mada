

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


private object ParallelFind {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Option[A] = {
        util.assert(!IsParallel(_1))

        if (_1.isEmpty) {
            None
        } else {
            _1.divide(_3).parallel(1).map(_.find(_2)).find(!_.isEmpty).getOrElse(None)
        }
    }
}
