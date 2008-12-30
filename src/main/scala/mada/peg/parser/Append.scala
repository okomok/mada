

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Append {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new AppendParser(p, q)
}

class AppendParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
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
}
