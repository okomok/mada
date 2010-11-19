

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Generate[+A](_1: Reactive[_], _2: Iterative[A]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: => Unit) {
        val it = _2.begin
        val _k = eval.Lazy{k;close()}
        if (!it) {
            _k()
        } else {
            _1 _for { _ =>
                if (it) {
                    f(~it)
                    it.++
                    if (!it) {
                        _k()
                    }
                }
            } _then {
                _k()
            }
        }
    }
}
