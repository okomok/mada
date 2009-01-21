

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object VectorPeg {
    def apply[A1](w: Vector[A1]): Peg[A1] = apply[A1, A1](w, vec.stl.EqualTo)
    def apply[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean): Peg[A2] = new VectorPeg(w, pred)
}

class VectorPeg[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean) extends Peg[A2] {
    override def parse(v: Vector[A2], first: Int, last: Int): Int = {
        val vsize = w.size
        if (last - first < vsize) {
            Peg.FAILURE
        } else if (vec.stl.Equal(w, 0, vsize, v, first, pred)) {
            first + vsize
        } else {
            Peg.FAILURE
        }
    }

    override def length = w.size
}
