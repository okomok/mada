

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Lazy {
    def apply[A](v: => Vector[A]): Vector[A] = new LazyVector(v)
}

private[mada] class LazyVector[A](v: => Vector[A]) extends VectorProxy[A] {
    override lazy val self = v
}
