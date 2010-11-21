

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class OnExit[+A](_1: Reactive[A], _2: Exit => Unit) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            f(x)
        } _then { q =>
            _2(q)
            k(q)
        }
    }
}
