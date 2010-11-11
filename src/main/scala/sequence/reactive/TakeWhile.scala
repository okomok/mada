

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A, B >: A](_1: Reactive[A], _2: A => Boolean, _3: (B => Unit) => Unit = function.empty1) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) {
        val k = eval.ByLazy{close;_3(f)}
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                k()
            }
        }
    }

    override def then(f: => Unit): Reactive[B] = TakeWhile[A, B](_1, _2, g => {_3(g);f}) // _1.onClose(f).takeWhile(_2)
    override def then_++[C >: B](that: Reactive[C]): Reactive[C] = TakeWhile[A, C](_1, _2, g => {_3(g);that.foreach(g)})
}
