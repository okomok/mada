

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object And {
    def apply[A](p: Peg[A], q: Peg[A]): Peg[A] = new AndPeg(p, q)
}

class AndPeg[A](p: Peg[A], q: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val pcur = p.parse(v, first, last)
        if (pcur != FAILED) {
            val qcur = q.parse(v, first, last) // short-circuit
            if (pcur == qcur) {
                pcur
            } else {
                FAILED
            }
        } else {
            FAILED
        }
    }

    override def length = p.length
}


object AndIf {
    def apply[A](p: Peg[A], pred: Vector[A] => Boolean): Peg[A] = apply(p, Vector.triplify(pred))
    def apply[A](p: Peg[A], pred: (Vector[A], Long, Long) => Boolean): Peg[A] = new AndIfPeg(p, pred)
}

class AndIfPeg[A](override val self: Peg[A], pred: (Vector[A], Long, Long) => Boolean) extends PegProxy[A] {
    override def parse(v: Vector[A], first: Long, last: Long): Long = {
        val cur = self.parse(v, first, last)
        if (cur == FAILED || !pred(v, first, cur)) {
            FAILED
        } else {
            cur
        }
    }
}
