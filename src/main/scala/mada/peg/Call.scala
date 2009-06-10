

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Call[A](_1: util.ByName[Unit]) extends Forwarder[A] {
    override protected val delegate = eps[A] act { _ => _1() }
}
