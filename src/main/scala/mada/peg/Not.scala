

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Not {
    def apply[A](p: Peg[A]): Peg[A] = new NotPeg(p)
}

private[mada] class NotPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p.parse(v, start, end) != Peg.FAILURE) {
            Peg.FAILURE
        } else {
            val cur = start + p.length
            if (cur <= end) {
                cur
            } else {
                Peg.FAILURE
            }
        }
    }

    override def length = p.length
}
