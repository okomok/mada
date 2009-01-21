

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object AsVectorOf {
    def apply[A, B](v: Vector[A]): Vector[B] = new AsVectorOfVector[A, B](v)
}

class AsVectorOfVector[A, B](override val * : Vector[A]) extends VectorAdapter[A, B] {
    override def apply(i: Int) = *(i).asInstanceOf[B]
    override def update(i: Int, e: B) = *(i) = e.asInstanceOf[A]
}
