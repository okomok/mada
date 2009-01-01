

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object After {
    def apply[A](p: Parser[A]): Parser[A] = new AfterParser(p)
}

class AfterParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val len = p.length
        if (first < len) {
            FAILED
        } else {
            if (first == p.parse(s, first - len, first)) {
                first
            } else {
                FAILED
            }
        }
    }

    override def length = 0
}
