

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Filter[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        _1.activate(reactor.make(_ => k.onEnd, e => if (_2(e)) k.react(e)))
    }
}


case class Remove[A](_1: Reactive[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}
