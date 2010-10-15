

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[A](_1: Reactive[A], _2: Reactive[_], _3: Reactive[A] => Unit = Closer) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        @volatile var go = true
        val g = util.ByLazy{_3(_1);_2.close}
        for (y <- _2) {
            go = false
            g()
        }

        for (x <- _1) {
            if (go) {
                f(x)
            } else {
                g()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeUntil[A](_1, _2, r => {f;_3(r)})
}
