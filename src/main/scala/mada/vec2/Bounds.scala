

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class BoundsVector[A](override val * : Vector[A]) extends Adapter[A, A] {
    override def apply(i: Long) = {
        if (i < 0 || i >= *.size) {
            throw new IndexOutOfBoundsException(i.toString)
        }
        *(i)
    }
    override def update(i: Long, e: A) = {
        if (i < 0 || i >= *.size) {
            throw new IndexOutOfBoundsException(i.toString)
        }
        *(i) = e
    }
}
