

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


@notThreadSafe
private[reactive]
case class Until[+A](_1: Reactive[A], _2: Reactive[_]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var isEnd = false
        _2.onBegin{isEnd = true}

        for (x <- _1) {
            if (!isEnd) {
                f(x)
            }
        }
    }
}
