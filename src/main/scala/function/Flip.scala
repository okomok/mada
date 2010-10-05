

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


private
case class Flip[-T1, -T2, +R](_1: Function2[T1, T2, R]) extends Function2[T2, T1, R] {
    override def apply(x: T2, y: T1) = _1(y, x)
}
