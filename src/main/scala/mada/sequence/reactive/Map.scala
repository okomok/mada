

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Map[A, +B](_1: Reactive[A], _2: A => B) extends Reactive[B] {
    override def activate(k: Reactor[B]) = {
        _1.activate(reactor.make(_ => k.onEnd, e => k.react(_2(e))))
    }
}
