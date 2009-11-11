

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Take[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "take")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private val _onEnd = new DoFirstTime[Unit](_ => k.onEnd)
            private val _react = new DoTimes[A](_2, e => k.react(e), _onEnd)
            override def onEnd = _onEnd()
            override def react(e: A) = _react(e)
        }
        _1.subscribe(j)
    }

    override def take(n: Int) = _1.take(Math.min(_2, n)) // take-take fusion
}
