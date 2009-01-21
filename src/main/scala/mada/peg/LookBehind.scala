

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookBehind {
    def apply[A](p: Peg[A]): Peg[A] = new LookBehindPeg(p)
}

class LookBehindPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val len = p.length
        if (start < len) {
            Peg.FAILURE
        } else {
            if (start == p.parse(v, start - len, start)) {
                start
            } else {
                Peg.FAILURE
            }
        }
    }

    override def length = 0
}
