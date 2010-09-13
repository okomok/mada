

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Tail[+A](_1: Reactive[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var first = true
        for (x <- _1) {
            if (first) {
                first = false
            } else {
                f(x)
            }
        }
    }
}
