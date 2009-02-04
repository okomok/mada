

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object SeqAnd {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new SeqAndPeg(p, q)
}

private[mada] class SeqAndPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        var cur = p.parse(v, start, end)
        if (cur == Peg.FAILURE) {
            Peg.FAILURE
        } else {
            q.parse(v, cur, end)
        }
    }

    override def length = p.length + q.length
}
