

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Append[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        _1.activate(reactor.make(_ => _2.activate(k), e => k.react(e)))
    }
}
