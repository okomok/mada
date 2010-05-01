

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


case class ByName[+A](_1: util.ByName[Iterative[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
