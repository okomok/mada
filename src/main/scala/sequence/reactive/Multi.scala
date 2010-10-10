

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Multi[A](_1: Reactive[A => Unit]) extends (A => Unit) {
    override def apply(x: A) = for (f <- _1) f(x)
}
