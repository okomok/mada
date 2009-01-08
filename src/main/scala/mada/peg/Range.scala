

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Range {
    def apply[A](i: A, j: A)(implicit c: A => Ordered[A]): Peg[A] = new RangePeg(i, j)(c)
}

class RangePeg[A](i: A, j: A)(implicit c: A => Ordered[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        if (first == last) {
            return FAILURE
        } else {
            val e = v(first)
            if (c(i) <= e && c(e) <= j) {
                first + 1
            } else {
                FAILURE
            }
        }
    }

    override def length = 1
}
