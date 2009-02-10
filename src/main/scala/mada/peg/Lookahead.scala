

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Lookahead {
    def apply[A](p: Peg[A]): Peg[A] = new LookaheadPeg(p)
}

private[mada] class LookaheadPeg[A](p: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p.parse(v, start, end) != Peg.FAILURE) {
            start
        } else {
            Peg.FAILURE
        }
    }
}
