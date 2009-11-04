

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Map[A, +B](_1: Reactive[A], _2: A => B) extends Reactive[B] {
    override def subscribe(t: Reactor[B]) = _1.subscribe(
        new Reactor[A] {
            override def onEnd = t.onEnd
            override def react(e: A) = t.react(f(e))
        }
    )
}
