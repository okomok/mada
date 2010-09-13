

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
case class Map[A, +B](_1: Auto[A], _2: A => B) extends Auto[B] {
    override def foreach(f: B => Unit): Unit = for (x <- _1) { f(_2(x)) }
}
