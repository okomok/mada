

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object FromVector {
    def apply[A1](v: Vector[A1]): Parser[A1] = apply[A1, A1](v, vec.stl.EqualTo)
    def apply[A1, A2](v: Vector[A1], pred: (A1, A2) => Boolean): Parser[A2] = new VectorParser(v, pred)
}

class VectorParser[A1, A2](v: Vector[A1], pred: (A1, A2) => Boolean) extends Parser[A2] {
    override def parse(s: Scanner[A2], first: Long, last: Long): Long = {
        val vsize = v.size
        if (last - first < vsize) {
            FAILED
        } else if (vec.stl.Equal(v, 0, vsize, s, first, pred)) {
            first + vsize
        } else {
            FAILED
        }
    }
}
