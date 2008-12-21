

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object AsVectorOf {
    def apply[A, B](v: Vector[A]): Vector[B] = new AsVectorOfVector[A, B](v)
}

class AsVectorOfVector[A, B](override val * : Vector[A]) extends Adapter[A, B] {
    override def apply(i: Long) = *(i).asInstanceOf[B]
    override def update(i: Long, e: B) = *(i) = e.asInstanceOf[A]
}
