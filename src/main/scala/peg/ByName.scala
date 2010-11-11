

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class ByName[A](_1: eval.ByName[Peg[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
