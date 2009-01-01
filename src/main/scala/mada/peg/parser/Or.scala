

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Or {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new OrParser(p, q)
}

class OrParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = p.parse(s, first, last)
        if (cur == FAILED) {
            q.parse(s, first, last)
        } else {
            cur
        }
    }

    override def length = {
        val plen = p.length
        if (plen != q.length) {
            throw new UnsupportedOperationException("OrParser.length")
        } else {
            plen
        }
    }
/*
    override def lookBehind = p.lookBehind or q.lookBehind
    override def not = p.not seqAnd q.not
*/
}
