

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class AsVectorOf[A, B](_1: Vector[A]) extends Adapter[A, B] {
    override protected val underlying = _1
}
