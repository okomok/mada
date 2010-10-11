

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Adjacent[A](_1: Vector[A]) extends Forwarder[(A, A)] {
    override protected val delegate = if (_1.size <= 1) empty[(A, A)] else _1.zip(_1.tail)
}
