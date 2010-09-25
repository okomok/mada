

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[+A](_1: Reactive[A], _2: Reactive[_], _3: util.ByName[Unit] = util.byName(())) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        @volatile var go = true
        val g = util.byLazy(_3())
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

    override def endWith(f: => Unit): Reactive[A] = TakeUntil(_1, _2, util.byName{_3();f})
}
