

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Then {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new ThenParser(p, q)
}

class ThenParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = p.parse(s, first, last)
        if (cur == FAILED) {
            FAILED
        } else {
            cur = q.parse(s, cur, last)
            if (cur == FAILED) {
                FAILED
            } else {
                cur
            }
        }
    }

    override def length = p.length + q.length
}
