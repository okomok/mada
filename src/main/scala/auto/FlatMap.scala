

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class FlatMap[A, +B](_1: Auto[A], _2: A => Auto[B]) extends Auto[B] {
    override def foreach(f: B => Unit): Unit = for (x <- _1) { for (y <- _2(x)) f(y) }
}
