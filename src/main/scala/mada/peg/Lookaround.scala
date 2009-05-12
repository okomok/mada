

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Lookaround3 {
    def apply[A](p: vector.Pred3[A]): Peg[A] = new Lookaround3Peg[A](p)
}

private[mada] class Lookaround3Peg[A](p: vector.Pred3[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p(v, start, end)) {
            start
        } else {
            FAILURE
        }
    }
}
