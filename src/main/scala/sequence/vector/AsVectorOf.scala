

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class AsVectorOf[A, B](_1: Vector[A]) extends Adapter[A, B] {
    override protected val underlying = _1
}
