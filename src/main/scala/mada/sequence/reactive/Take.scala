

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Take[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "take")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            // A lock-free algorithm can't be found (as far as you need early onEnd),
            // for onEnd and react must not be overlapped.
            private var count = _2
            private val _onEnd = util.byLazy(k.onEnd)
            override def onEnd = _onEnd()
            override def react(e: A) = {
                if (count != 0) {
                    k.react(e)
                    count -= 1
                    if (count == 0) {
                        _onEnd()
                    }
                }
            }
        }
        _1.subscribe(j)
    }

    override def take(n: Int) = _1.take(Math.min(_2, n)) // take-take fusion
}
