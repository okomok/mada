

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


sealed abstract class Result extends Any {
    type self <: Result

    final override  def asInstanceOfOrderingResult = self
    final override type asInstanceOfOrderingResult = self

     def ===[that <: Result](that: that): ===[that]
    type ===[that <: Result] <: Boolean

    final  def !==[that <: Result](that: that): !==[that] = ===(that).not
    final type !==[that <: Result] = ===[that]#not

    private[mada]  def isLT: isLT
    private[mada] type isLT <: Boolean
    private[mada]  def isGT: isGT
    private[mada] type isGT <: Boolean
    private[mada]  def isEQ: isEQ
    private[mada] type isEQ <: Boolean

    final override type undual = scala.Int
    override def canEqual(that: scala.Any) = that.isInstanceOf[Ordering]
}


sealed abstract class LT extends Result {
    override  val self = this
    override type self = LT

    override  def ===[that <: Result](that: that): ===[that] = that.isLT
    override type ===[that <: Result] = that#isLT

    override private[mada]  def isLT: isLT = `true`
    override private[mada] type isLT = `true`
    override private[mada]  def isGT: isGT = `false`
    override private[mada] type isGT = `false`
    override private[mada]  def isEQ: isEQ = `false`
    override private[mada] type isEQ = `false`

    override  def undual: undual = -1
}

sealed abstract class GT extends Result {
    override  val self = this
    override type self = GT

    override  def ===[that <: Result](that: that): ===[that] = that.isGT
    override type ===[that <: Result] = that#isGT

    override private[mada]  def isLT: isLT = `false`
    override private[mada] type isLT = `false`
    override private[mada]  def isGT: isGT = `true`
    override private[mada] type isGT = `true`
    override private[mada]  def isEQ: isEQ = `false`
    override private[mada] type isEQ = `false`

    override  def undual: undual = 1
}

sealed abstract class EQ extends Result {
    override  val self = this
    override type self = EQ

    override  def ===[that <: Result](that: that): ===[that] = that.isEQ
    override type ===[that <: Result] = that#isEQ

    override private[mada]  def isLT: isLT = `false`
    override private[mada] type isLT = `false`
    override private[mada]  def isGT: isGT = `false`
    override private[mada] type isGT = `false`
    override private[mada]  def isEQ: isEQ = `true`
    override private[mada] type isEQ = `true`

    override  def undual: undual = 0
}


private[mada] object _Result {
    val LT = new LT{}
    val GT = new GT{}
    val EQ = new EQ{}
}
