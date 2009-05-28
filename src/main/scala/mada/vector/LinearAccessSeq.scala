

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


// from ++ from ++ ... is not conforming RandomAccess, so `force` or the following is needed.

private[mada] object ToLinearAccessSeq {
    def apply[A](from: Vector[A]): Seq[A] = new VectorLinearAccessSeq(from)
}

private[mada] class VectorLinearAccessSeq[A](from: Vector[A]) extends Seq[A] {
    override def apply(i: Int) = from(i)
    override def iterator = from.iterator
    override val length = from.size
}
