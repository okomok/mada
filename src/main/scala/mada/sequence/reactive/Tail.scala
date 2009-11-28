

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Tail[+A](_1: Reactive[A]) extends Reactive[A] {
    override def start(k: Reactor[A]) = {
        _1.start(reactor.make(_ => k.onEnd, new SkipFirst[A](e => k.react(e))))
    }
}
