

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class ByName[+A](_1: util.ByName[Reactive[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
