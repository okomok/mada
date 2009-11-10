

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Take[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "take")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var c = _2
            override def onEnd = ()
            override def react(e: A) = {
                if (c != 0) {
                    k.react(e)
                    c -= 1
                    if (c == 0) {
                        k.onEnd
                    }
                }
            }
        }
        _1.subscribe(j)
    }

    override def take(n: Int) = _1.take(Math.min(_2, n)) // take-take fusion
}
