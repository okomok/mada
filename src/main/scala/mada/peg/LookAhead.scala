

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookAhead {
    def apply[A](p: Peg[A]): Peg[A] = new LookAheadPeg(p)
}

class LookAheadPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (p.parse(v, first, last) != FAILURE) {
            first
        } else {
            FAILURE
        }
    }

    override def length = 0
}