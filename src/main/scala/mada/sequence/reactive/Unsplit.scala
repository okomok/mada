

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Unsplit[A](_1: Reactive[Reactive[A]], _2: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[Reactive[A]] {
            private val pre = new IfFirst[Unit](_ => (), _ => _2.subscribe(k.noEnd))
            override def onEnd = k.onEnd
            override def react(e: Reactive[A]) = { pre(); e.subscribe(k.noEnd) }
        }
        _1.subscribe(j)
    }
}
