

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookingAt {
    def apply[A](p: Peg[A], v: Vector[A]): Option[Int] = {
        val (x, first, last) = v.triple
        val cur = p.parse(x, first, last)
        if (cur == Peg.FAILURE) {
            None
        } else {
            Some(cur)
        }
    }
}
