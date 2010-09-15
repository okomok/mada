

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class TakeUntil[+A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = true
        _2.onStart{go = false}.start

        for (x <- _1) {
            if (go) {
                f(x)
            }
        }
    }

    override def onEnd(f: => Unit): Reactive[A] = TakeUntilThen(_1, _2, util.byLazy(f))
}


@notThreadSafe
private[reactive]
case class TakeUntilThen[+A](_1: Reactive[A], _2: Reactive[_], _3: util.ByLazy[Unit]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = true
        _2.onStart{go = false; _3()}.start

        for (x <- _1) {
            if (go) {
                f(x)
            } else {
                _3()
            }
        }
    }
}
