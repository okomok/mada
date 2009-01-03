

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object FromVector {
    def apply[A1](w: Vector[A1]): Parser[A1] = apply[A1, A1](w, vec.stl.EqualTo)
    def apply[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean): Parser[A2] = new VectorParser(w, pred)
}

class VectorParser[A1, A2](w: Vector[A1], pred: (A1, A2) => Boolean) extends Parser[A2] {
    override def parse(v: Vector[A2], first: Long, last: Long): Long = {
        val vsize = w.size
        if (last - first < vsize) {
            FAILED
        } else if (vec.stl.Equal(w, 0, vsize, v, first, pred)) {
            first + vsize
        } else {
            FAILED
        }
    }

    override def length = w.size
}
