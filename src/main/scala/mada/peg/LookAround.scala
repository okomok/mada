

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LookAround3 {
    def apply[A](p: Vector.Pred3[A]): Peg[A] = new LookAround3Peg[A](p)
}

private[mada] class LookAround3Peg[A](p: Vector.Pred3[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p(v, start, end)) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}
