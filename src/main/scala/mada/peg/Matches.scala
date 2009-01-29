

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Matches {
    def apply[A](p: Peg[A], v: Vector[A]): Boolean = {
        p.parse(v, v.start, v.end) == v.end
    }
}
