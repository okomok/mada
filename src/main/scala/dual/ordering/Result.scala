

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package ordering


sealed abstract class Result extends Any {
    type self <: Result
    type asInstanceOfOrderingResult = self

     def ===[that <: Result](that: that): ===[that]
    type ===[that <: Result] <: Boolean

     def !==[that <: Result](that: that): !==[that]
    type !==[that <: Result] <: Boolean

     def isLT: isLT
    type isLT <: Boolean
     def isGT: isGT
    type isGT <: Boolean
     def isEQ: isEQ
    type isEQ <: Boolean

     def isLTEQ: isLTEQ
    type isLTEQ <: Boolean
     def isGTEQ: isGTEQ
    type isGTEQ <: Boolean

    final override type undual = scala.Int
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Result]
}


private[dual]
sealed abstract class AbstractResult extends Result {
    final override  def !==[that <: Result](that: that): !==[that] = ===(that).not
    final override type !==[that <: Result] =                        ===[that]#not

    final override  def isLTEQ: isLTEQ = isLT  || isEQ
    final override type isLTEQ         = isLT# ||[isEQ]
    final override  def isGTEQ: isGTEQ = isGT  || isEQ
    final override type isGTEQ         = isGT# ||[isEQ]
}


sealed abstract class LT extends AbstractResult {
    type self = LT

    override  def ===[that <: Result](that: that): ===[that] = that.isLT
    override type ===[that <: Result]                        = that#isLT

    override  def isLT: isLT = `true`
    override type isLT       = `true`
    override  def isGT: isGT = `false`
    override type isGT       = `false`
    override  def isEQ: isEQ = `false`
    override type isEQ       = `false`

    override  def undual: undual = -1
}

sealed abstract class GT extends AbstractResult {
    type self = GT

    override  def ===[that <: Result](that: that): ===[that] = that.isGT
    override type ===[that <: Result]                        = that#isGT

    override  def isLT: isLT = `false`
    override type isLT       = `false`
    override  def isGT: isGT = `true`
    override type isGT       = `true`
    override  def isEQ: isEQ = `false`
    override type isEQ       = `false`

    override  def undual: undual = 1
}

sealed abstract class EQ extends AbstractResult {
    type self = EQ

    override  def ===[that <: Result](that: that): ===[that] = that.isEQ
    override type ===[that <: Result]                        = that#isEQ

    override  def isLT: isLT = `false`
    override type isLT       = `false`
    override  def isGT: isGT = `false`
    override type isGT       = `false`
    override  def isEQ: isEQ = `true`
    override type isEQ       = `true`

    override  def undual: undual = 0
}


private[dual]
object _Result {
    val LT = new LT{}
    val GT = new GT{}
    val EQ = new EQ{}
}
