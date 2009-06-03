

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class AsVectorOf[A, B](_1: Vector[A]) extends Adapter[A, B] {
    override val underlying = _1
}
