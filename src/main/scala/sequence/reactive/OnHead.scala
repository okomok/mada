

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class OnHead[A](_1: Reactive[A], _2: util.ByName[Unit]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        var go = true
        for (x <- _1) {
            if (go) {
                go = false
                _2()
            }
            f(x)
        }
    }
}
