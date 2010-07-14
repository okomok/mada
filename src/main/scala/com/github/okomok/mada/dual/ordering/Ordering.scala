

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


object Ordering extends Common


trait Ordering extends Any {
    type self <: Ordering

    final override  def asInstanceOfOrdering = self
    final override type asInstanceOfOrdering = self

     def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y]
    type compare[x <: Any, y <: Any] <: Result

    final def `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0](x: x, y: y, flt: flt, fgt: fgt, feq: feq): `match`[x, y, flt, fgt, feq] =
        Match(self, x, y, flt, fgt, feq).apply
    final type `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0] =
        Match[self, x, y, flt, fgt, feq]#apply

    final override  def undual: undual = new scala.Ordering[scala.Any] { override def compare(x: scala.Any, y: scala.Any) = unsupported("dual.Ordering.undual") }
    final override type undual = scala.Ordering[scala.Any]
    final override  def canEqual(that: scala.Any) = that.isInstanceOf[Ordering]
}
