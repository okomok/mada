

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Reverse {
    def apply[A](v: Vector[A]): Vector[A] = new ReverseVector(v)
}

private[mada] class ReverseVector[A](v: Vector[A]) extends Adapter.Proxy[A] {
    override val self = v.permutation({ i => v.size - i - 1 })
    override def reverse = v // reverse-reverse fusion
}
