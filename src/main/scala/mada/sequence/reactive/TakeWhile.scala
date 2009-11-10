

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class TakeWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var ends = false
            override def onEnd = ()
            override def react(e: A) = {
                if (!ends) {
                    if (_2(e)) {
                        k.react(e)
                    } else {
                        ends = true
                        k.onEnd
                    }
                }
            }
        }
        _1.subscribe(_1.beforeSubscribe(j))
    }
}
