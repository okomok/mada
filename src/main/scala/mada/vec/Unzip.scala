

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Unzip {
    def apply[A, B](v: Vector[(A, B)]): (Vector[A], Vector[B]) = (new UnzipVector1(v), new UnzipVector2(v))
}

class UnzipVector1[A, B](v: Vector[(A, B)]) extends Vector[A] {
    override def size = v.size
    override def apply(i: Long) = v(i)._1
}

class UnzipVector2[A, B](v: Vector[(A, B)]) extends Vector[B] {
    override def size = v.size
    override def apply(i: Long) = v(i)._2
}
