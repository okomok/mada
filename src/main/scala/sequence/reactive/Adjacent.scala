

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


// drop . zip

private
case class Adjacent[A](_1: Reactive[A]) extends Reactive[(A, A)] {
    override def close = _1.close
    override def foreach(f: Tuple2[A, A] => Unit) = {
        var prev: Option[A] = None
        for (x <- _1) {
            if (!prev.isEmpty) {
                f(prev.get, x)
            }
            prev = Some(x)
        }
    }
}
