

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Seal {
    def apply[A](v: Vector[A]): Vector[A] = new SealVector(v)
}

private[mada] sealed class SealVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] {
    // seal-seal fusion too must be "sealed".
}
