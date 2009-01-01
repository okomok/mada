

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Range {
    def apply[A](i: A, j: A)(implicit c: A => Ordered[A]): Parser[A] = new RangeParser(i, j)(c)
}

class RangeParser[A](i: A, j: A)(implicit c: A => Ordered[A]) extends Parser[A] {
    override def parse(s: Scanner[A], first: Long, last: Long): Long = {
        if (first == last) {
            return FAILED
        } else {
            val e = s(first)
            if (c(i) <= e && c(e) <= j) {
                first + 1
            } else {
                FAILED
            }
        }
    }

    override def length = 1
}
