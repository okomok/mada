

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


object Ordering extends Common


trait Ordering extends Equiv {
    type self <: Ordering
    type asInstanceOfOrdering = self

     def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y]
    type compare[x <: Any, y <: Any] <: Result

    final  def lteq[x <: Any, y <: Any](x: x, y: y): lteq[x, y] = compare(x, y).isLTEQ
    final type lteq[x <: Any, y <: Any] = compare[x, y]#isLTEQ

    final  def gteq[x <: Any, y <: Any](x: x, y: y): gteq[x, y] = compare(x, y).isGTEQ
    final type gteq[x <: Any, y <: Any] = compare[x, y]#isGTEQ

    final  def lt[x <: Any, y <: Any](x: x, y: y): lt[x, y] = compare(x, y).isLT
    final type lt[x <: Any, y <: Any] = compare[x, y]#isLT

    final  def gt[x <: Any, y <: Any](x: x, y: y): gt[x, y] = compare(x, y).isGT
    final type gt[x <: Any, y <: Any] = compare[x, y]#isGT

    final override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = compare(x, y).isEQ
    final override type equiv[x <: Any, y <: Any] = compare[x, y]#isEQ

    final  def `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0](x: x, y: y, flt: flt, fgt: fgt, feq: feq): `match`[x, y, flt, fgt, feq] =
        Match(self, x, y, flt, fgt, feq).apply
    final type `match`[x <: Any, y <: Any, flt <: Function0, fgt <: Function0, feq <: Function0] =
        Match[self, x, y, flt, fgt, feq]#apply
}
