

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Step[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "step")

    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var c = _2
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (c == _2) {
                    k.react(e)
                }
                c -= 1
                if (c == 0) {
                    c = _2
                }
            }
        }
        _1.subscribe(j)
    }

    override def step(n: Int) = _1.step(_2 * n) // step-step fusion
}
