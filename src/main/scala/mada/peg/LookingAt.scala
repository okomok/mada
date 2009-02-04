

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LookingAt {
    def apply[A](p: Peg[A], v: Vector[A]): Option[Int] = {
        val cur = p.parse(v, v.start, v.end)
        if (cur == Peg.FAILURE) {
            None
        } else {
            Some(cur)
        }
    }
}
