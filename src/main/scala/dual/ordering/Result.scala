

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

     def isLT: isLT
    type isLT <: Boolean
     def isGT: isGT
    type isGT <: Boolean
     def isEQ: isEQ
    type isEQ <: Boolean

    final  def isLTEQ: isLTEQ = isLT || isEQ
    final type isLTEQ = isLT# ||[isEQ]
    final  def isGTEQ: isGTEQ = isGT || isEQ
    final type isGTEQ = isGT# ||[isEQ]

    final override type undual = scala.Int
    override def canEqual(that: scala.Any) = that.isInstanceOf[Result]
}


sealed abstract class LT extends Result {
    override  val self = this
    override type self = LT

    override  def ===[that <: Result](that: that): ===[that] = that.isLT
    override type ===[that <: Result] = that#isLT

    override  def isLT: isLT = `true`
    override type isLT = `true`
    override  def isGT: isGT = `false`
    override type isGT = `false`
    override  def isEQ: isEQ = `false`
    override type isEQ = `false`

    override  def undual: undual = -1
}

sealed abstract class GT extends Result {
    override  val self = this
    override type self = GT

    override  def ===[that <: Result](that: that): ===[that] = that.isGT
    override type ===[that <: Result] = that#isGT

    override  def isLT: isLT = `false`
    override type isLT = `false`
    override  def isGT: isGT = `true`
    override type isGT = `true`
    override  def isEQ: isEQ = `false`
    override type isEQ = `false`

    override  def undual: undual = 1
}

sealed abstract class EQ extends Result {
    override  val self = this
    override type self = EQ

    override  def ===[that <: Result](that: that): ===[that] = that.isEQ
    override type ===[that <: Result] = that#isEQ

    override  def isLT: isLT = `false`
    override type isLT = `false`
    override  def isGT: isGT = `false`
    override type isGT = `false`
    override  def isEQ: isEQ = `true`
    override type isEQ = `true`

    override  def undual: undual = 0
}


private[mada] object _Result {
    val LT = new LT{}
    val GT = new GT{}
    val EQ = new EQ{}
}
