

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class UnfoldRight[A, +B](_1: A, _2: A => Option[(B, A)]) extends Reactive[B] {
    override def foreach(f: B => Unit) = {
        var acc = _2(_1)
        while (!acc.isEmpty) {
            f(acc.get._1)
            acc = _2(acc.get._2)
        }
    }
}
