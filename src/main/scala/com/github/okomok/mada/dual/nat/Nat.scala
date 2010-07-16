

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


object Nat extends Common with OperatorCommon {
    @returnThis
    val Operator: OperatorCommon = this
}


/**
 * The dual natural number metatrait
 */
trait Nat extends Any {
    type self <: Nat

    final override  def asInstanceOfNat = self
    final override type asInstanceOfNat = self

     def isZero: isZero
    type isZero <: Boolean

     def increment: increment
    type increment <: Nat

     def decrement: decrement
    type decrement <: Nat

     def +[that <: Nat](that: that): +[that]
    type +[that <: Nat] <: Nat

     def -[that <: Nat](that: that): -[that]
    type -[that <: Nat] <: Nat

     def **[that <: Nat](that: that): **[that]
    type **[that <: Nat] <: Nat

     def ===[that <: Nat](that: that): ===[that]
    type ===[that <: Nat] <: Boolean

     def <=[that <: Nat](that: that): <=[that]
    type <=[that <: Nat] <: Boolean

     def <[that <: Nat](that: that): <[that]
    type <[that <: Nat] <: Boolean

    final  def !==[that <: Nat](that: that): !==[that] = ===(that).not
    final type !==[that <: Nat] = ===[that]#not

    final  def >[that <: Nat](that: that): >[that] = that < self
    final type >[that <: Nat] = that# <[self]

    final  def >=[that <: Nat](that: that): >=[that] = that <= self
    final type >=[that <: Nat] = that# <=[self]

     def &[that <: Nat](that: that): &[that]
    type &[that <: Nat] <: Nat

     def |[that <: Nat](that: that): |[that]
    type |[that <: Nat] <: Nat

     def toDense: toDense
    type toDense <: Dense

     def toPeano: toPeano
    type toPeano <: Peano

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Nat]
}
