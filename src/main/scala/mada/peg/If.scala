

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object If {
    def apply[A](p: Vector.Pred[A]): Peg[A] = If3(Vector.triplify(p))
}

private[mada] object If3 {
    def apply[A](p: Vector.Pred3[A]): Peg[A] = new If3Peg[A](p)
}

private[mada] class If3Peg[A](p: Vector.Pred3[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (p(v, start, end)) {
            start
        } else {
            Peg.FAILURE
        }
    }

    override def width = 0
}
