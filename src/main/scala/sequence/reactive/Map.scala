

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Map[A, +B](_1: Reactive[A], _2: A => B) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) = for (x <- _1) { f(_2(x)) }
}
