

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A, B >: A](_1: Reactive[A], _2: A => Boolean) extends Reactive[B] {
    override def close() = _1.close()
    override def foreach(f: B => Unit, k: => Unit) {
        val _k = eval.Lazy{close();k}
        _1 _for { x =>
            if (_2(x)) {
                f(x)
            } else {
                _k()
            }
        } _then {
            k()
        }
    }
}
