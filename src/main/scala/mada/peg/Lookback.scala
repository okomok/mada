

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Lookback {
    def apply[A](p: Peg[A]): Peg[A] = new LookbackPeg(p)
}

private[mada] class LookbackPeg[A](p: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val x = v(v.start, start).reverse
        if (p.parse(x, x.start, x.end) == Peg.FAILURE) {
            Peg.FAILURE
        } else {
            start
        }
    }
}