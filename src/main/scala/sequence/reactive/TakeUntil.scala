

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeUntil[A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def close() = { _1.close(); _2.close() }
    override def forloop(f: A => Unit, k: => Unit) {
        @volatile var go = true
        val _k = eval.Lazy{close();k}
        _2 _for { y =>
            go = false
            _k()
        } _then {
            _k()
        }
        _1 _for { x =>
            if (go) {
                f(x)
            } else {
                _k()
            }
        } _then {
            _k()
        }
    }
}
