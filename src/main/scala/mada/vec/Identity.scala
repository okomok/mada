

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Identity {
    def apply[A](v: Vector[A]): Vector[A] = new IdentityVector(v)
}

class IdentityVector[A](override val self: Vector[A]) extends VectorProxy[A]
