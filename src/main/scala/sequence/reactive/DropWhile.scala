

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class DropWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        var go = false
        _1 _for { x =>
            if (!go && !_2(x)) {
                go = true
            }
            if (go) {
                f(x)
            }
        } _then {
            k
        }
    }
}
