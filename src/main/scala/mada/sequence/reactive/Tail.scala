

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Tail[+A](_1: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private val s = function.skipFirst[A](e => k.react(e))
            override def onEnd = k.onEnd
            override def react(e: A) = s(e)
        }
        _1.subscribe(j)
    }
}
