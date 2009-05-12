

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Minus {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new MinusPeg(p, q)
}

private[mada] class MinusPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val pcur = p.parse(v, start, end)
        if (pcur == FAILURE) {
            FAILURE
        } else {
            val qcur = q.parse(v, start, end)
            if (qcur == FAILURE || qcur < pcur) {
                pcur
            } else {
                FAILURE
            }
        }
    }

    override def width = p.width
}
