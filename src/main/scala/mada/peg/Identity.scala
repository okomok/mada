

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Identity[A](_1: Peg[A]) extends Forwarder[A] {
    override protected val delegate = _1
}
