

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Or {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new OrPeg(p, q)
}

private[mada] class OrPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        var cur = p.parse(v, start, end)
        if (cur == FAILURE) {
            q.parse(v, start, end)
        } else {
            cur
        }
    }

    override def width = {
        val plen = p.width
        if (plen != q.width) {
            throw new NotConstantWidth("or")
        } else {
            plen
        }
    }
}
