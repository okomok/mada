

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Init[+A](_1: Reactive[A]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: => Unit) {
        var prev: Option[A] = None
        _1 _for { x =>
            if (!prev.isEmpty) {
                f(prev.get)
            }
            prev = Some(x)
        } _then {
            k
        }
    }
}
