

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class FlatMap[A, +B](_1: Reactive[A], _2: A => Reactive[B]) extends Reactive[B] {
    override def subscribe(k: Reactor[B]) = {
        val j = new Reactor[A] {
            override def onEnd = k.onEnd
            override def react(e: A) = _2(e).subscribe(k.noEnd)
        }
        _1.subscribe(j)
    }
}
