

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class OnEnd[+A](_1: Reactive[A], _2: util.ByLazy[Unit]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = false
        for (x <- _1) {
            go = true
            f(x)
        }
        if (go)
            _2()
    }
}
