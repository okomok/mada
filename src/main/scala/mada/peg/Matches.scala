

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Matches {
    def apply[A](p: Peg[A], v: Vector[A]): Boolean = {
        val (x, first, last) = v.triple
        p.parse(x, first, last) == last
    }
}
