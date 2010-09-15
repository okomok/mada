

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class OnStart[+A](_1: Reactive[A], _2: util.ByLazy[Unit]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        for (x <- _1) {
            _2()
            f(x)
        }
    }
}
