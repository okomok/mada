

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Tail[+A](_1: Reactive[A]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        var first = true
        _1 _for { x =>
            if (first) {
                first = false
            } else {
                f(x)
            }
        } _then {
            k
        }
    }
}
