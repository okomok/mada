

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object LookBehind {
    def apply[A](p: Parser[A]): Parser[A] = new LookBehindParser(p)
}

class LookBehindParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val len = p.length
        if (first < len) {
            FAILED
        } else {
            if (first == p.parse(v, first - len, first)) {
                first
            } else {
                FAILED
            }
        }
    }

    override def length = 0
}
