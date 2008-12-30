

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object StarUntil {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = new StarUntilParser(p, q)
}

class StarUntilParser[A](p: Parser[A], q: Parser[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        var cur = first

        var test = q.parse(s, cur, last)
        while (test == FAILED) {
            test = p.parse(s, cur, last)
            if (test == FAILED) {
                return FAILED
            } else {
                cur = test
                test = q.parse(s, cur, last)
            }
        }

        test
    }
}


object StarBefore {
    def apply[A](p: Parser[A], q: Parser[A]): Parser[A] = p.starUntil(q.before)
}
