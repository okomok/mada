

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Synchronize[+A](_1: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = _1.subscribe(k)

    override def beforeSubscribe[B](k: Reactor[B]) = new reactor.Forwarder[B] {
        override protected val delegate = k
        override def onEnd = synchronized { delegate.onEnd }
        override def react(e: B) = synchronized { delegate.react(e) }
    }
}
