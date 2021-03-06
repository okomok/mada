

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Shift[+A](_1: Reactive[A], _2: (=> Unit) => Unit) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            _2{f(x)}
        } _then { q =>
            _2{k(q)}
        }
    }
}

private
case class ShiftReact[A](_1: Reactive[A], _2: A => (A => Unit) => Unit) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            _2(x)(f)
        } _then {
            // FIXEME how to shift k?
            k
        }
    }
}
