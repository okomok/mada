

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class DropWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def foreach(f: A => Unit) = {
        var go = false
        for (x <- _1) {
            if (!go && !_2(x)) go = true
            if (go) f(x)
        }
    }
}
