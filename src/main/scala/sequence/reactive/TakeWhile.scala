

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A](_1: Reactive[A], _2: A => Boolean, _3: Reactive[A] => Unit = Closer) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit): Unit = {
        val g = util.byLazy(_3(_1))
        for (x <- _1) {
            if (_2(x)) {
                f(x)
            } else {
                g()
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = TakeWhile(_1, _2, (r: Reactive[A]) => {f;_3(r)})
}
