

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


object Nat extends nat.Common


/**
 * The dual natural number
 */
trait Nat extends Any {
    type self <: Nat

    final override  def asNat = self
    final override type asNat = self

    @constantTime
     def isZero: isZero
    type isZero <: Boolean

    @constantTime
     def increment: increment
    type increment <: Nat

    @constantTime
     def decrement: decrement
    type decrement <: Nat

     def plus[that <: Nat](that: that): plus[that]
    type plus[that <: Nat] <: Nat

     def minus[that <: Nat](that: that): minus[that]
    type minus[that <: Nat] <: Nat

     def times[that <: Nat](that: that): times[that]
    type times[that <: Nat] <: Nat

     def divMod[that <: Nat](that: that): divMod[that]
    type divMod[that <: Nat] <: Product2

     def exp[that <: Nat](that: that): exp[that]
    type exp[that <: Nat] <: Nat

     def equal[that <: Nat](that: that): equal[that]
    type equal[that <: Nat] <: Boolean

     def nequal[that <: Nat](that: that): nequal[that]
    type nequal[that <: Nat] <: Boolean

     def lteq[that <: Nat](that: that): lteq[that]
    type lteq[that <: Nat] <: Boolean

     def lt[that <: Nat](that: that): lt[that]
    type lt[that <: Nat] <: Boolean

     def div[that <: Nat](that: that): div[that]
    type div[that <: Nat] <: Nat

     def mod[that <: Nat](that: that): mod[that]
    type mod[that <: Nat] <: Nat

     def gt[that <: Nat](that: that): gt[that]
    type gt[that <: Nat] <: Boolean

     def gteq[that <: Nat](that: that): gteq[that]
    type gteq[that <: Nat] <: Boolean

     def bitAnd[that <: Nat](that: that): bitAnd[that]
    type bitAnd[that <: Nat] <: Nat

     def bitOr[that <: Nat](that: that): bitOr[that]
    type bitOr[that <: Nat] <: Nat

    final override type undual = scala.Int
    final override  def canEqual(that: scala.Any) = that.isInstanceOf[Nat]
}
