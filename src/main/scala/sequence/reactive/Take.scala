

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Take[+A](_1: Reactive[A], _2: Int, _3: (A => Unit) => Unit = function.empty1) extends Reactive[A] {
    override def close() = _1.close()
    override def foreach(f: A => Unit) {
        def k = {close();_3(f)}
        if (_2 == 0) {
            k
        } else {
            var c = _2
            for (x <- _1) {
                if (c != 0) {
                    f(x)
                    c -= 1
                    if (c == 0) {
                        k
                    }
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = Take[A](_1, _2, g => {_3(g);f}) // _1.onClose(f).take(_2)
    override def then_++[B >: A](that: Reactive[B]): Reactive[B] = Take[B](_1, _2, g => {_3(g);that.foreach(g)})
    // override def take(n: Int): Reactive[A] = _1.take(java.lang.Math.min(_2, n)) // take.take fusion
}
