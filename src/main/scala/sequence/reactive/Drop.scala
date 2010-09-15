

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Drop[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "drop")

    override def foreach(f: A => Unit) = {
        var c = _2
        for (x <- _1) {
            if (c == 0) {
                f(x)
            } else {
                c -= 1
            }
        }
    }

    override def drop(n: Int): Reactive[A] = _1.drop(_2 + n) // drop-drop fusion
}
