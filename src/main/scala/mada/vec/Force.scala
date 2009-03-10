

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Force {
    def apply[A](v: Vector[A]): Vector[A] = new ForceVector(v)
}

private[mada] class ForceVector[A](v: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A] {
    override val underlying = Vector.fromArray(v.toArray)
    override def force = this // force-force fusion
}
