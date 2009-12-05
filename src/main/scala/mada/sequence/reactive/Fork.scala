

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Fork[A](_1: Reactive[A], _2: Reactive[A] => Unit) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        var one = _1
        val two = new Reactive[A] {
            override def subscribe(j: Reactor[A]) = { one = _1.forkTo(j) }
        }
        _2(two)
        one.subscribe(k)
    }
}


case class ForkTo[A](_1: Reactive[A], _2: Reactor[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        _1.subscribe(reactor.make(_ => { _2.onEnd; k.onEnd }, e => { _2.react(e); k.react(e) }))
    }
}


case class ForLoop[A](_1: Reactive[A], _2: A => Unit) extends Forwarder[A] {
    override protected val delegate = _1.forkTo(reactor.make(_ => (), e => _2(e)))
}

case class EndWith[+A](_1: Reactive[A], _2: util.ByName[Unit]) extends Forwarder[A] {
    override protected val delegate = _1.forkTo(reactor.make(_ => _2(), _ => ()))
}
