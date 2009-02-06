

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LookAhead {
    def apply[A](p: Peg[A]): Peg[A] = new LookAheadPeg(p)
}

private[mada] class LookAheadPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p.parse(v, start, end) != Peg.FAILURE) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}
