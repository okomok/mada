

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Take[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "take")

    override def foreach(f: A => Unit): Unit = {
        var c = _2
        for (x <- _1) {
            if (c == 0) {
                return
            } else {
                f(x)
                c -= 1
            }
        }
    }

    override def take(n: Int) = _1.take(java.lang.Math.min(_2, n)) // take-take fusion
}
