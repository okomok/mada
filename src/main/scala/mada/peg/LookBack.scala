

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LookBack {
    def apply[A](p: Peg[A]): Peg[A] = new LookBackPeg(p)
}

private[mada] class LookBackPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val x = v(v.start, start).reverse
        if (p.parse(x, x.start, x.end) == Pegs.FAILURE) {
            Pegs.FAILURE
        } else {
            start
        }
    }

    override def length = 0
}
