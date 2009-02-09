

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LookBehind {
    def apply[A](p: Peg[A]): Peg[A] = new LookBehindPeg(p)
}

private[mada] class LookBehindPeg[A](p: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val len = p.width
        if (start - len < v.start) {
            Peg.FAILURE
        } else {
            if (start == p.parse(v, start - len, start)) {
                start
            } else {
                Peg.FAILURE
            }
        }
    }
}
