

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Replace[A, +B](_1: Reactive[A], _2: Iterative[B], _3: Reactive[A] => Unit = Closer) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) {
        val it = _2.begin
        if (!it) {
            _3(_1)
        } else {
            for (_ <- _1) {
                if (it) {
                    f(~it)
                    it.++
                    if (!it) {
                        _3(_1)
                    }
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[B] = Replace[A, B](_1, _2, r => {f;_3(r)})
}
