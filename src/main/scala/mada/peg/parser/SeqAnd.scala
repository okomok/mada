

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object SeqAnd {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new SeqAndParser(p, q)
}

class SeqAndParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = p.parse(v, first, last)
        if (cur == FAILED) {
            FAILED
        } else {
            cur = q.parse(v, cur, last)
            if (cur == FAILED) {
                FAILED
            } else {
                cur
            }
        }
    }

    override def length = p.length + q.length
}
