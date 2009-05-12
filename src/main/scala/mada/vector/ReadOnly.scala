

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object ReadOnly {
    def apply[A](v: Vector[A]): Vector[A] = new ReadOnlyVector(v)
}

private[mada] class ReadOnlyVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] with Adapter.NotWritable[A]
