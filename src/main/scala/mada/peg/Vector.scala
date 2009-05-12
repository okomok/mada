

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object FromVector {
    def apply[A](from: Vector[A]): Peg[A] = apply(from, function.equal)
    def apply[A](from: Vector[A], pred: (A, A) => Boolean): Peg[A] = new VectorPeg(from, pred)
}

private[mada] class VectorPeg[A](from: Vector[A], pred: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val wsize = from.size
        if (end - start < wsize) {
            FAILURE
        } else if (stl.Equal(from, from.start, from.end, v, start, pred)) {
            start + wsize
        } else {
            FAILURE
        }
    }

    override def width = from.size
}
