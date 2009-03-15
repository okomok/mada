

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Range {
    def apply[A](i: A, j: A, c: Compare[A]): Peg[A] = new RangePeg(i, j, c)
}

private[mada] class RangePeg[A](i: A, j: A, c: Compare[A]) extends Peg[A] {
    private val ord = Compare.toOrdering(c)

    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (start == end) {
            Peg.FAILURE
        } else {
            val e = v(start)
            if (ord.lteq(i, e) && ord.lteq(e, j)) {
                start + 1
            } else {
                Peg.FAILURE
            }
        }
    }

    override def width = 1
}
