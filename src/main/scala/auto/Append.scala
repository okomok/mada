

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class Append[+A](_1: Auto[A], _2: Auto[A]) extends Auto[A] {
    override def foreach(f: A => Unit) = {
        for (x <- _1) f(x)
        for (y <- _2) f(y)
    }
}
