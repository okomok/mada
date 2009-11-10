

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Drop[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "drop")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var c = _2
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (c != 0) {
                    c -= 1
                } else {
                    k.react(e)
                }
            }
        }
        _1.subscribe(beforeSubscribe(j))
    }

    override def drop(n: Int) = _1.drop(_2 + n) // drop-drop fusion
}
