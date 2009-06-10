

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class ByName[A](_1: util.ByName[Peg[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
