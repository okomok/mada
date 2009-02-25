

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object FromVector {
    def apply[A](from: Vector[A]): Peg[A] = apply(from, Functions.equal)
    def apply[A](from: Vector[A], pred: (A, A) => Boolean): Peg[A] = new VectorPeg(from, pred)
}

private[mada] class VectorPeg[A](from: Vector[A], pred: (A, A) => Boolean) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        val wsize = from.size
        if (end - start < wsize) {
            Peg.FAILURE
        } else if (Stl.equal(from, from.start, from.end, v, start, pred)) {
            start + wsize
        } else {
            Peg.FAILURE
        }
    }

    override def width = from.size
}
