

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Star[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    throwIfZeroWidth(_1, "star")
    override protected val delegate = _1.repeat(0, ())
}
