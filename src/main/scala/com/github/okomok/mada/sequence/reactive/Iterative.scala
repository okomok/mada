

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class FromIterative[+A](_1: Iterative[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        _1.foreach{ e => k.react(e) }
        k.onEnd
    }
}


private[mada] case class ToIterative[A](_1: Reactive[A]) extends iterative.Forwarder[A] {
    private val q = new java.util.concurrent.ConcurrentLinkedQueue[A]
    _1.activate(reactor.make(_ => (), e => q.add(e)))
    override protected val delegate = Iterative.from(q)
}
