

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object After {
    def apply[A](p: Parser[A], n: Long): Parser[A] = new AfterParser(p, n)
}

class AfterParser[A](p: Parser[A], n: Long) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first < n) {
            FAILED
        } else {
            if (first == p.parse(s, first - n, first)) {
                first
            } else {
                FAILED
            }
        }
    }
}
