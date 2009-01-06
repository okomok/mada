

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Matches {
    def apply[A](p: Peg[A], v: Vector[A]): Boolean = {
        val (first, last) = v.toPair
        p.parse(v, first, last) == last
    }
}
