

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Times[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = _1.permutation{ i => Div.remainder(i, _1.size) }.nth(0, _1.size * _2).readOnly
    override def times(_n: Int) = _1.times(_2 * _n) // times.times fusion
}
