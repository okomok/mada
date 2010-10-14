

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Merge[+A](_1: Reactive[A], _2: Reactive[A]) extends Reactive[A] {
    override def close = { _1.close; _2.close }
    override def foreach(f: A => Unit) {
        for (x <- _1) f(x)
        for (y <- _2) f(y)
    }
}
