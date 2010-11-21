

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Catching[+A](_1: Reactive[A], _2: PartialFunction[Throwable, Unit]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        _1 _for { x =>
            try {
                f(x)
            } catch {
                case t: Throwable => {
                    if (_2.isDefinedAt(t)) {
                        _2(t)
                    } else {
                        throw t
                    }
                }
            }
        } _then {
            k
        }
    }
}
