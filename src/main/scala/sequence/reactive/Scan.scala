

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class ScanLeft[A, B](_1: Reactive[A], _2: B, _3: (B, A) => B) extends Reactive[B] {
    override def foreach(f: B => Unit) = {
        var acc = _2
        for (x <- _1) {
            f(acc)
            acc = _3(acc, x)
        }
        f(acc)
    }
}
