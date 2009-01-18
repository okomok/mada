

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookBehind {
    def apply[A](p: Peg[A]): Peg[A] = new LookBehindPeg(p)
}

class LookBehindPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long) = {
        val len = p.length
        if (first < len) {
            Peg.FAILURE
        } else {
            if (first == p.parse(v, first - len, first)) {
                first
            } else {
                Peg.FAILURE
            }
        }
    }

    override def length = 0
}
