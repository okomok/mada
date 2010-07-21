

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class Times[+A](_1: Iterative[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = repeat(()).take(_2).flatMap{ (u: Unit) => _1 }
}
