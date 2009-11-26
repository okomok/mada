

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Append[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        _1.subscribe(_2.subscribe(k), e => k.react(e))
    }
}
