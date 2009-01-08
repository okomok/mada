

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Not {
    def apply[A](p: Peg[A]): Peg[A] = new NotPeg(p)
}

class NotPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last || p.parse(v, first, last) != FAILURE) {
            return FAILURE
        } else {
            first + p.length
        }
    }

    override def length = p.length
}
