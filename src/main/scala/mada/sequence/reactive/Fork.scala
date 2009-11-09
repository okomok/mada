

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Fork[A](_1: Reactive[A], _2: Reactor[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            override def onEnd = { _2.onEnd; k.onEnd }
            override def react(e: A) = { _2.react(e); k.react(e) }
        }
        _1.subscribe(j)
    }
}


case class ForkBy[A](_1: Reactive[A], _2: A => Unit) extends Forwarder[A] {
    override protected val delegate = _1.fork(reactor.make(util.theUnit, _2))
}
