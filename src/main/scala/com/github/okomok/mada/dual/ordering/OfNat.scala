

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


private[mada] class OfNatPeano extends Ordering {
    override  def self = this
    override type self = OfNatPeano

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] =
        `if`(x.asInstanceOfNatPeano < y.asInstanceOfNatPeano, always0(LT), `if`(x.asInstanceOfNatPeano > y.asInstanceOfNatPeano, always0(GT), always0(EQ))).apply.asInstanceOfOrderingResult.asInstanceOf[compare[x, y]]
    override type compare[x <: Any, y <: Any] =
        `if`[x#asInstanceOfNatPeano# <[y#asInstanceOfNatPeano], always0[LT], `if`[x#asInstanceOfNatPeano# >[y#asInstanceOfNatPeano], always0[GT], always0[EQ]]]#apply#asInstanceOfOrderingResult
}


private[mada] class OfNatDense extends Ordering {
    override  def self = this
    override type self = OfNatDense

    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] =
        `if`(x.asInstanceOfNatDense < y.asInstanceOfNatDense, always0(LT), `if`(x.asInstanceOfNatDense > y.asInstanceOfNatDense, always0(GT), always0(EQ))).apply.asInstanceOfOrderingResult.asInstanceOf[compare[x, y]]
    override type compare[x <: Any, y <: Any] =
        `if`[x#asInstanceOfNatDense# <[y#asInstanceOfNatDense], always0[LT], `if`[x#asInstanceOfNatDense# >[y#asInstanceOfNatDense], always0[GT], always0[EQ]]]#apply#asInstanceOfOrderingResult
}
