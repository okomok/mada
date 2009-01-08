

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object SeqAnd {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new SeqAndPeg(p, q)
}

class SeqAndPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = p.parse(v, first, last)
        if (cur == FAILURE) {
            FAILURE
        } else {
            q.parse(v, cur, last)
        }
    }

    override def length = p.length + q.length
}
