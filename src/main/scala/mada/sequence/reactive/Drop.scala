

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Drop[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "drop")

    override def subscribe(k: Reactor[A]) = {
        _1.subscribe(k.onEnd, new SkipTimes[A](e => k.react(e), _2))
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
