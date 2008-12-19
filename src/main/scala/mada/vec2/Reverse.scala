

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class ReverseVector[A](override val * : Vector[A]) extends Adapter[A, A] {
    override def apply(i: Long) = *(size - i - 1)
    override def update(i: Long, e: A) = *(size - i - 1) = e

    override def reverse = *
}
