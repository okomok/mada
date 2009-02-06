

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Negate {
    def apply[A](p: Peg[A]): Peg[A] = new NegatePeg(p)
}

private[mada] class NegatePeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p.parse(v, start, end) != Peg.FAILURE) {
            Peg.FAILURE
        } else {
            val cur = start + p.width
            if (cur <= end) {
                cur
            } else {
                Peg.FAILURE
            }
        }
    }

    override def width = p.width
}
