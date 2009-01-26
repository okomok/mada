

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Unzip {
    def apply[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = (new UnzipVector1(v), new UnzipVector2(v))
}

private[mada] class UnzipVector1[A, B](v: Vector[(A, B)]) extends Vector[A] {
    override def size = v.size
    override def apply(i: Int) = v(i)._1
}

private[mada] class UnzipVector2[A, B](v: Vector[(A, B)]) extends Vector[B] {
    override def size = v.size
    override def apply(i: Int) = v(i)._2
}
