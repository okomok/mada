

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object And {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new AndPeg(p, q)
}

class AndPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val pcur = p.parse(v, start, end)
        if (pcur != Peg.FAILURE) {
            val qcur = q.parse(v, start, end) // short-circuit
            if (pcur == qcur) {
                pcur
            } else {
                Peg.FAILURE
            }
        } else {
            Peg.FAILURE
        }
    }

    override def length = p.length
}
