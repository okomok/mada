

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


// Same as `merge` in detail.

private
case class Header[+A](_1: Reactive[A], _2: Iterative[A]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        for (y <- _2) f(y)
        for (x <- _1) f(x)
    }
}
