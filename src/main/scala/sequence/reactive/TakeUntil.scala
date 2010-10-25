

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def close = { _1.close; _2.close }
    override def foreach(f: A => Unit) {
        @volatile var go = true
        val g = util.ByLazy{close}
        for (y <- _2) {
            go = false
            g()
        }

        for (x <- _1) {
            if (go) {
                f(x)
            } else {
                g()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = _1.onClose(f).takeUntil(_2)
}
