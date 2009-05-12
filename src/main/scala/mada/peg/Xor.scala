

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Xor {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new XorPeg(p, q)
}

private[mada] class XorPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val pcur = p.parse(v, start, end)
        val qcur = q.parse(v, start, end)

        val pok = pcur != FAILURE
        if (pok && qcur != FAILURE) {
            FAILURE
        } else if (pok) {
            pcur
        } else {
            qcur
        }
    }
}
