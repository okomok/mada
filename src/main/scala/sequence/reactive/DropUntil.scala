

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class DropUntil[A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def close() = { _1.close(); _2.close() }
    override def foreach(f: A => Unit, k: => Unit) {
        @volatile var go = false
        val g = eval.Lazy{_2.close()}

        for (y <- _2) {
            go = true
            g()
        }

        _1 _for { x =>
            if (go) {
                g()
                f(x)
            }
        } _then {
            k
        }
    }
}
