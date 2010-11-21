

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package arm


import sequence.Reactive
import sequence.reactive.Exit


private
case class UsedBy[A, +B](_1: Arm[A], _2: A => Reactive[B]) extends Reactive[B] {
    override def close() = _1.close()
    @pre("f is synchronous")
    override def forloop(f: B => Unit, k: Exit => Unit) {
        val r = _1.open
        _2(r) _for { y =>
            for (_ <- from(_1: java.io.Closeable)) {
                f(y)
            }
        } _then { q =>
            k(q)
            close()
        }
    }
}
