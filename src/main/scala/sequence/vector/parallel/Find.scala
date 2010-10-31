

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
object ParallelFind {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Option[A] = {
        assert(!IsParallel(_1))

        if (_1.isEmpty) {
            None
        } else {
            _1.divide(_3).parallelBy(1).map(_.find(_2)).join.find(!_.isEmpty).getOrElse(None)
/*
            // This is one-path algorithm for hundreds of cores.
            var r: Option[A] = None
            _1.divide(_3).parallelBy(1).map(_.find(_2)).pareach { x =>
                if (!x.isEmpty && r.isEmpty) {
                    r = x
                }
            }
            r
*/
        }
    }
}
