

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LookBack {
    def apply[A](p: Peg[A]): Peg[A] = new LookBackPeg(p)
}

class LookBackPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val (w, i, j) = v.window(0, first).reverse.toTriple
        if (p.parse(w, i, j) == FAILED) {
            FAILED
        } else {
            first
        }
    }

    override def length = 0
}
