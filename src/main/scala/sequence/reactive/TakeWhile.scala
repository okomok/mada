

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class TakeWhile[A, B >: A](_1: Reactive[A], _2: A => Boolean) extends Reactive[B] {
    override def close() = _1.close()
    override def forloop(f: B => Unit, k: Exit => Unit) {
        val _k = IfFirst[Exit] { q => k(q);close() } Else { _ => () }
        _1 _for { x =>
            if (_2(x)) {
                f(x)
            } else {
                _k(End)
            }
        } _then { q =>
            _k(q)
        }
    }
}
