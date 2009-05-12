

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object SeqImply {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new SeqImplyPeg(p, q)
}

private[mada] class SeqImplyPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val cur = p.parse(v, start, end)
        if (cur == FAILURE) {
            start
        } else {
            q.parse(v, cur, end)
        }
    }
}
