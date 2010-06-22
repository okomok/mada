

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Flatten[+A](_1: Reactive[Reactive[A]]) extends Forwarder[A] {
    override protected val delegate = _1.unsplit(empty)
}
