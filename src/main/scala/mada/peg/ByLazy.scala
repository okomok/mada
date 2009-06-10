

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class ByLazy[A](_1: util.ByLazy[Peg[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
