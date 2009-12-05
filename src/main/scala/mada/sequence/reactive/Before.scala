

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
case class Before[+A](_1: Reactive[A], _2: Reactive[Any]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val _onEnd1 = new OnlyFirst[Unit](_ => k.onEnd)
        _2.subscribe(reactor.make(_ => _onEnd1(), _ => _onEnd1()))
        _1.subscribe(reactor.make(_ => _onEnd1(), e => if (!_onEnd1.isDone) k.react(e)))
    }
}
