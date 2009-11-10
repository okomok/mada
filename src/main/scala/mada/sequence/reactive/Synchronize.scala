

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Synchronize[+A](_1: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = _1.subscribe(k)

    override def beforeSubscribe[B](k: Reactor[B]) = new Reactor[B] {
        override def onEnd = synchronized { k.onEnd }
        override def react(e: B) = synchronized { k.react(e) }
    }
}
