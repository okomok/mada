

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Merge[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private val _onEnd = new SkipFirst[Unit](_ => k.onEnd)
            override def onEnd = _onEnd()
            override def react(e: A) = k.react(e)
        }
        _1.activate(j)
        _2.activate(j)
    }
}
