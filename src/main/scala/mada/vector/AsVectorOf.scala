

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object AsVectorOf {
    def apply[A, B](v: Vector[A]): Vector[B] = new AsVectorOfVector[A, B](v)
}

private[mada] class AsVectorOfVector[A, B](override val underlying: Vector[A]) extends Adapter[A, B]
