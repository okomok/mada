

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
case class Connect[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        var ends1 = false
        def stop1 = if (!ends1) ends1 = true
        _2.activate(reactor.make(_ => { stop1; k.onEnd }, e => { stop1; k.react(e) }))
        _1.activate(reactor.make(_ => (), e => if (!ends1) k.react(e)))
    }
}
