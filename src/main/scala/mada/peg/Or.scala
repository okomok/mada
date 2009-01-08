

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Or {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new OrPeg(p, q)
}

class OrPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = p.parse(v, first, last)
        if (cur == FAILURE) {
            q.parse(v, first, last)
        } else {
            cur
        }
    }

    override def length = {
        val plen = p.length
        if (plen != q.length) {
            throw new UnsupportedOperationException("OrPeg.length")
        } else {
            plen
        }
    }
/*
    override def lookBehind = p.lookBehind or q.lookBehind
    override def not = p.not seqAnd q.not
*/
}
