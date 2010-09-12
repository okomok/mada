

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Unsplit[A](_1: Reactive[Sequence[A]], _2: Reactive[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        val j = new Reactor[Sequence[A]] {
            private val sep = new IfFirst[Unit](_ => (), _ => _2.activate(k.noEnd))
            override def onEnd = k.onEnd
            override def react(e: Sequence[A]) = { sep(); e.asReactive.activate(k.noEnd) }
        }
        _1.activate(j)
    }
}
