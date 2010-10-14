

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Take[A](_1: Reactive[A], _2: Int, _3: Reactive[A] => Unit = Closer) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        if (_2 == 0) {
            _3(_1)
        } else {
            var c = _2
            for (x <- _1) {
                if (c != 0) {
                    f(x)
                    c -= 1
                    if (c == 0) {
                        _3(_1)
                    }
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[A] = Take(_1, _2, (r: Reactive[A]) => {f;_3(r)})
//    override def take(n: Int): Reactive[A] = _1.take(java.lang.Math.min(_2, n)) // take-take fusion
}
