

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        @volatile var go = true
        val g = util.ByLazy{close;_2.close}
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
}
