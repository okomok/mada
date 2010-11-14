

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class OnHead[A](_1: Reactive[A], _2: A => Unit) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        var go = true
        for (x <- _1) {
            if (go) {
                go = false
                _2(x)
            }
            f(x)
        }
    }
}

private
case class OnNth[A](_1: Reactive[A], _2: Int, _3: A => Unit) extends Forwarder[A] {
    override protected val delegate = _1.fork{ r => r.drop(_2).onHead(_3) }
}
