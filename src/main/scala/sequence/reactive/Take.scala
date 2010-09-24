

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Take[+A](_1: Reactive[A], _2: Int, _3: util.ByName[Unit] = util.byName(())) extends Reactive[A] {
    override def foreach(f: A => Unit): Unit = {
        var c = _2
        for (x <- _1) {
            if (c != 0) {
                f(x)
                c -= 1
                if (c == 0) {
                    _3()
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = {
        Precondition.positive(_2, "take.then")
        Take(_1, _2, util.byName{_3();f})
    }

//    override def take(n: Int): Reactive[A] = _1.take(java.lang.Math.min(_2, n)) // take-take fusion
}
