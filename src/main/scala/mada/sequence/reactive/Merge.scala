

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Merge[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var eitherEnds = false
            override def onEnd = {
                if (eitherEnds) {
                    k.onEnd
                } else {
                    eitherEnds = true
                }
            }
            override def react(e: A) = k.react(e)
        }

        val i = _1.beforeSubscribe(j)
        _1.subscribe(i)
        _2.subscribe(i)
    }
}
