

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


// unneeded if NVI were known in the java world.

private[mada] object Bounds {
    def apply[A](v: Vector[A]): Vector[A] = new BoundsVector(v)
}

private[mada] class BoundsVector[A](override val underlying: Vector[A]) extends VectorAdapter[A, A] {
    override def mapIndex(i: Int) = { ThrowIf.outOfBounds(underlying, i); i }
    override def bounds = this // bounds-bounds fusion
}
