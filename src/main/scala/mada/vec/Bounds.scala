

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Bounds {
    def apply[A](v: Vector[A]): Vector[A] = new BoundsVector(v)
}

private[mada] class BoundsVector[A](override val self: Vector[A]) extends Adapter.Proxy[A] {
    override def isDefinedAt(i: Int) = self.start <= i && i < self.end
    override def bounds = this // bounds-bounds fusion
}
