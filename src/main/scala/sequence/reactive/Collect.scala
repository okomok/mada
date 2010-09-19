

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Collect[A, +B](_1: Reactive[A], _2: PartialFunction[A, B]) extends Forwarder[B] {
    override protected val delegate = _1.filter(_2.isDefinedAt).map(_2)
}
