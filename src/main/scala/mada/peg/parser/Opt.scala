

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Opt {
    def apply[A](p: Parser[A]): Parser[A] = new OptParser(p)
}

class OptParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val cur = p.parse(s, first, last)
        if (cur == FAILED) {
            first
        } else {
            cur
        }
    }
}
