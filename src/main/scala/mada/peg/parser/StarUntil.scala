

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object StarUntil {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new StarUntilParser(p, q)
}

class StarUntilParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        var cur = first

        var next = q.parse(v, cur, last)
        while (next == FAILED) {
            next = p.parse(v, cur, last)
            if (next == FAILED) {
                return FAILED
            } else {
                cur = next
                next = q.parse(v, cur, last)
            }
        }

        next
    }
}
