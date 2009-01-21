

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookBack {
    def apply[A](p: Peg[A]): Peg[A] = new LookBackPeg(p)
}

class LookBackPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Int, last: Int) = {
        val (x, i, j) = v(0, first).reverse.triple
        if (p.parse(x, i, j) == Peg.FAILURE) {
            Peg.FAILURE
        } else {
            first
        }
    }

    override def length = 0
}
