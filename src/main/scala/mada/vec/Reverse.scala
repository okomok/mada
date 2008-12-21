

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Reverse {
    def apply[A](v: Vector[A]): Vector[A] = new ReverseVector(v)
}

class ReverseVector[A](override val * : Vector[A]) extends Adapter[A, A] {
    override def mapIndex(i: Long) = size - i - 1
    override def reverse = *
}
