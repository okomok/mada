

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Identity {
    def apply[A](v: Vector[A]): Vector[A] = new IdentityVector(v)
}

private[mada] class IdentityVector[A](override val self: Vector[A]) extends VectorProxy[A] {
    override def identity = this // identity-identity fusion
}
