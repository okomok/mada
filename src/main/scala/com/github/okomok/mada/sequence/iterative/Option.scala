

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class FromOption[A](_1: Option[A]) extends Forwarder[A] {
    override protected val delegate = if (_1.isEmpty) empty else single(_1.get)
}
