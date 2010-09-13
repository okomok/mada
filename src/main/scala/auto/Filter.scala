

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class Filter[A](_1: Auto[A], _2: A => Boolean) extends Auto[A] {
    override def foreach(f: A => Unit): Unit = for (x <- _1) { if (_2(x)) f(x) }
}
