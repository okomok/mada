

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object ByLazy {
    def apply[A](v: => Vector[A]): Vector[A] = new ByLazyVector(v)
}

private[mada] class ByLazyVector[A](v: => Vector[A]) extends Forwarder[A] {
    override lazy val self = v
}
