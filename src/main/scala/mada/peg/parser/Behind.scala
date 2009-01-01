

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Behind {
    def apply[A](p: Parser[A]): Parser[A] = new BehindParser(p)
}

class BehindParser[A](p: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        val (v, i, j) = s.window(0, first).reverse.toTriple
        if (p.parse(s.copy(v), i, j) == FAILED) {
            FAILED
        } else {
            first
        }
    }

    override def length = 0
}
