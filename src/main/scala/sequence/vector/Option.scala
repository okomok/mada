

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class FromOption[A](_1: Option[A]) extends Vector[A] {
    override def start = 0
    override def end = if (_1.isEmpty) 0 else 1
    override def apply(i: Int) = _1.get
}
