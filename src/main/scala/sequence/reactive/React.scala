

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class React[A](_1: Reactive[A], _2: A => Unit) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            _2(x)
            f(x)
        } _then {
            k
        }
    }
}

private
case class ReactMatch[A](_1: Reactive[A], _2: PartialFunction[A, Unit]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            if (_2.isDefinedAt(x)) {
                _2(x)
            }
            f(x)
        } _then {
            k
        }
    }
}
