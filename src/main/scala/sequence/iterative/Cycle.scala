

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private
case class Cycle[+A](_1: Iterative[A]) extends Forwarder[A] {
    override protected val delegate = {
        Precondition.notEmpty(_1, "cycle")
        repeat(()).flatMap{ (u: Unit) => _1 }
    }
}
