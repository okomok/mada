

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object ReadOnly {
    def apply[A](v: Vector[A]): Vector[A] = new ReadOnlyVector(v)
}

private[mada] class ReadOnlyVector[A](override val self: Vector[A]) extends Adapter.Proxy[A] with NotWritable[A] {
    override def readOnly = this
}
