

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Xor {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new XorPeg(p, q)
}

class XorPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Int, last: Int) = {
        val pcur = p.parse(v, first, last)
        val qcur = q.parse(v, first, last)

        val pok = pcur != Peg.FAILURE
        if (pok && qcur != Peg.FAILURE) {
            Peg.FAILURE
        } else if (pok) {
            pcur
        } else {
            qcur
        }
    }
}
