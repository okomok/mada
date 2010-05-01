

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


// step 0 is meaningful?

@notThreadSafe
case class Step[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.positive(_2, "step")

    override def activate(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var c = 0
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (c == 0) {
                    k.react(e)
                }
                c += 1
                if (c == _2) {
                    c = 0
                }
            }
        }
        _1.activate(j)
    }

    override def step(n: Int) = _1.step(_2 * n) // step-step fusion
}
