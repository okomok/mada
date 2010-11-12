

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[A](_1: Reactive[A], _2: Reactive[_], _3: (A => Unit) => Unit = function.empty1) extends Reactive[A] {
    override def close = { _1.close; _2.close }
    override def foreach(f: A => Unit) {
        @volatile var go = true
        val k = eval.Lazy{close;_3(f)}
        for (y <- _2) {
            go = false
            k()
        }

        for (x <- _1) {
            if (go) {
                f(x)
            } else {
                k()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeUntil[A](_1, _2, g => {_3(g);f}) // _1.onClose(f).takeUntil(_2)
    override def then_++[B >: A](that: Reactive[B]): Reactive[B] = TakeUntil[B](_1, _2, g => {_3(g);that.foreach(g)})
}
