

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
case class TakeWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var ends = false
            private val _onEnd = util.byLazy(k.onEnd)
            override def onEnd = _onEnd()
            override def react(e: A) = {
                if (!ends) {
                    if (_2(e)) {
                        k.react(e)
                    } else {
                        ends = true
                        _onEnd()
                    }
                }
            }
        }
        _1.subscribe(j)
    }
}
