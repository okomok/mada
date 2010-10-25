

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Generate[A, +B](_1: Reactive[A], _2: Iterative[B]) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) {
        val it = _2.begin
        if (!it) {
            close
        } else {
            for (_ <- _1) {
                if (it) {
                    f(~it)
                    it.++
                    if (!it) {
                        close
                    }
                }
            }
        }
    }

    override def then(f: => Unit): Reactive[B] = _1.onClose(f).generate(_2)
}
