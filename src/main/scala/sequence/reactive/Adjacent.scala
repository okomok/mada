

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Adjacent[+A](_1: Reactive[A]) extends Forwarder[(A, A)] {
    override protected val delegate = _1.adjacentBy{ (a, b) => (a, b) }
}

private
case class AdjacentBy[A, +B](_1: Reactive[A], _2: (A, A) => B) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) = {
        var prev: Option[A] = None
        for (x <- _1) {
            if (!prev.isEmpty) {
                f(_2(prev.get, x))
            }
            prev = Some(x)
        }
    }
}
