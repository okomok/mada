

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Take[A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        if (_2 == 0) {
            close
        } else {
            var c = _2
            for (x <- _1) {
                if (c != 0) {
                    f(x)
                    c -= 1
                    if (c == 0) {
                        close
                    }
                }
            }
        }
    }

    override def take(n: Int): Reactive[A] = _1.take(java.lang.Math.min(_2, n)) // take-take fusion
}
