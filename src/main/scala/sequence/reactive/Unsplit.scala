

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Unsplit[A](_1: Reactive[Sequence[A]], _2: Reactive[A]) extends Reactive[A] {
    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        var first = true
        _1 _for { s =>
            if (first) {
                first = false
            } else {
                for (x <- _2) {
                    f(x)
                }
            }
            for (x <- s.asReactive) {
                f(x)
            }
        } _then {
            k
        }
    }
}
