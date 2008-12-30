

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


// one character parser

object Not {
    def apply[A](p: Parser[A]): Parser[A] = new NotParser(p)
}

class NotParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == last || p.parse(s, first, last) != FAILED) {
            return FAILED
        } else {
            first + 1
        }
    }

    override def not = p
}
