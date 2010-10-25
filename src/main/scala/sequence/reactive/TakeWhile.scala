

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit): Unit = {
        val g = util.ByLazy{close}
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                g()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = _1.onClose(f).takeWhile(_2)
}
