

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat


object Nat extends Common with OperatorCommon {
    @returnThis
    val Operator: OperatorCommon = this
}


/**
 * The dual natural number
 */
trait Nat extends Any {
    type self <: Nat
    type asInstanceOfNat = self

    @constantTime
     def isZero: isZero
    type isZero <: Boolean

    @constantTime
     def increment: increment
    type increment <: Nat

    @constantTime
     def decrement: decrement
    type decrement <: Nat

     def +[that <: Nat](that: that): +[that]
    type +[that <: Nat] <: Nat

     def -[that <: Nat](that: that): -[that]
    type -[that <: Nat] <: Nat

     def **[that <: Nat](that: that): **[that]
    type **[that <: Nat] <: Nat

     def divMod[that <: Nat](that: that): divMod[that]
    type divMod[that <: Nat] <: Product2

     def ^[that <: Nat](that: that): ^[that]
    type ^[that <: Nat] <: Nat

     def ===[that <: Nat](that: that): ===[that]
    type ===[that <: Nat] <: Boolean

     def <=[that <: Nat](that: that): <=[that]
    type <=[that <: Nat] <: Boolean

     def <[that <: Nat](that: that): <[that]
    type <[that <: Nat] <: Boolean

     def !==[that <: Nat](that: that): !==[that]
    type !==[that <: Nat] <: Boolean

     def /[that <: Nat](that: that): /[that]
    type /[that <: Nat] <: Nat

     def %[that <: Nat](that: that): %[that]
    type %[that <: Nat] <: Nat

     def >[that <: Nat](that: that): >[that]
    type >[that <: Nat] <: Boolean

     def >=[that <: Nat](that: that): >=[that]
    type >=[that <: Nat] <: Boolean

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
