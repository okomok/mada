

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Reverse {
    def apply[A](v: Vector[A]): Vector[A] = new ReverseVector(v)
}

private[mada] class ReverseVector[A](override val underlying: Vector[A]) extends VectorAdapter[A, A] {
    override def mapIndex(i: Int) = underlying.end - (i - underlying.start) - 1

    override def reverse = underlying // reverse-reverse fusion
}
