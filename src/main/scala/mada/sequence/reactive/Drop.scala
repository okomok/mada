

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Drop[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "drop")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var _react = function.skip[A](_2, e => k.react(e))
            override def onEnd = k.onEnd
            override def react(e: A) = _react(e)
        }
        _1.subscribe(j)
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
