

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Opt[A](_1: Peg[A]) extends QuantifiedForwarder[A] {
    override protected val delegate = _1.repeat(0, 1)
}
