

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object VectorIterable {
    def apply[A](v: Vector[A]): Iterable[A] = new VectorIterable(v)
}

class VectorIterable[A](v: Vector[A]) extends Iterable[A] {
    override def elements = v.elements
}
