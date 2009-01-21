

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// v ++ v ++ ... is not conforming RandomAccess, so `force` or the following is needed.

object LinearAccessSeq {
    def apply[A](v: Vector[A]): Seq[A] = new VectorLinearAccessSeq(v)
}

class VectorLinearAccessSeq[A](v: Vector[A]) extends Seq[A] {
    override def apply(i: Int) = v(i)
    override def elements = v.elements
    override val length = v.size
}
