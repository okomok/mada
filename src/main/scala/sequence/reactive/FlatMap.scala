

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class FlatMap[A, +B](_1: Reactive[A], _2: A => Reactive[B]) extends Reactive[B] {
    override def foreach(f: B => Unit) = for (x <- _1) { for (y <- _2(x)) f(y) }
}
