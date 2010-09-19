

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[+A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = true
        for (y <- _2) {
            go = false
        }

        for (x <- _1) {
            if (go) {
                f(x)
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeUntilThen(_1, _2, util.byName(f))
}


private
case class TakeUntilThen[+A](_1: Reactive[A], _2: Reactive[_], _3: util.ByName[Unit]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = true
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
}
