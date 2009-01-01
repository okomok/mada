

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object LookAhead {
    def apply[A](p: Parser[A]): Parser[A] = new LookAheadParser(p)
}

class LookAheadParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (p.parse(s, first, last) != FAILED) {
            first
        } else {
            FAILED
        }
    }

    override def length = 0
}
