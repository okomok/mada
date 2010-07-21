

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class Unstringize(_1: String) extends Forwarder[Char] {
    override protected val delegate = Iterative.fromSIterable(_1)
}
