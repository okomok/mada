

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Take[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "take")

    override def foreach(f: A => Unit): Unit = {
        @volatile var c = _2
        for (x <- _1) {
            if (c != 0) {
                f(x)
                c -= 1
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeThen(_1, _2, util.byName(f))
    override def take(n: Int): Reactive[A] = _1.take(java.lang.Math.min(_2, n)) // take-take fusion
}


private
case class TakeThen[+A](_1: Reactive[A], _2: Int, _3: util.ByName[Unit]) extends Reactive[A] {
    Precondition.positive(_2, "takeThen")

    override def foreach(f: A => Unit): Unit = {
        @volatile var c = _2
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
}
