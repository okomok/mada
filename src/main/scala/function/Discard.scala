

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


private
case class Discard[-T, +U](_1: T => U) extends Function1[T, Unit] {
    override def apply(x: T) = _1(x)
}
