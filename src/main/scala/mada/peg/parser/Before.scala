

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Before {
    def apply[A](p: Parser[A]): Parser[A] = new BeforeParser(p)
}

class BeforeParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (p.parse(s, first, last) != FAILED) {
            first
        } else {
            FAILED
        }
    }
}
