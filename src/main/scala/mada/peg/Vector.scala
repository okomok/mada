

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object VectorPeg {
    def apply[A1](w: Vector[A1]): Peg[A1] = apply[A1, A1](w, Functions.equal)
    def apply[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean): Peg[A2] = new VectorPeg(w, pred)
}

private[mada] class VectorPeg[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean) extends Peg[A2] {
    override def parse(v: Vector[A2], start: Int, end: Int): Int = {
        val wsize = w.size
        if (end - start < wsize) {
            Peg.FAILURE
        } else if (Stl.equal(w, 0, wsize, v, start, pred)) {
            start + wsize
        } else {
            Peg.FAILURE
        }
    }

    override def length = w.size
}
