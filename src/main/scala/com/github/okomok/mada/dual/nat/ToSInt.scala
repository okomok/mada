

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package nat


private[mada] object ToSInt {
     def apply[x <: Nat](x: x): apply[x] = if_Any(x.isZero, new Always(0), new Step(x)).apply.asInstanceOf[scala.Int]
    type apply[x <: Nat] = scala.Int

    class Always(n: scala.Int) extends Function0_Any {
        override  def apply = n
        override type apply = scala.Int
    }
    class Step[x <: Nat](x: x) extends Function0_Any {
        override  def apply = 1 + x.decrement.toSInt
        override type apply = scala.Int
    }
}
