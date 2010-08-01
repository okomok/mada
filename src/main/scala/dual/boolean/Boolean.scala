

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package boolean


object Boolean extends Common


/**
 * The dual Boolean
 */
sealed abstract class Boolean extends Any {
    type self <: Boolean
    type asInstanceOfBoolean = self

     def not: not
    type not <: Boolean

     def ===[that <: Boolean](that: that): ===[that]
    type ===[that <: Boolean] <: Boolean

     def !==[that <: Boolean](that: that): !==[that]
    type !==[that <: Boolean] <: Boolean

     def &&[that <: Boolean](that: that): &&[that]
    type &&[that <: Boolean] <: Boolean

     def ||[that <: Boolean](that: that): ||[that]
    type ||[that <: Boolean] <: Boolean

     def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else]
    type `if`[then <: Function0, _else <: Function0] <: Function0

    private[mada]  def isTrue: isTrue
    private[mada] type isTrue <: Boolean

    private[mada]  def isFalse: isFalse
    private[mada] type isFalse <: Boolean

    final override type undual = scala.Boolean
    final override def canEqual(that: scala.Any) = that.isInstanceOf[Boolean]
}


private[mada] sealed abstract class AbstractBoolean extends Boolean {
    final override  def !==[that <: Boolean](that: that): !==[that] = ===(that).not
    final override type !==[that <: Boolean] =                        ===[that]#not

//    final override protected  def typeid = _Boolean.typeid
//    final override protected type typeid = _Boolean.typeid
}


/**
 * The true
 */
sealed abstract class `true` extends AbstractBoolean {
    type self = `true`

    override  def not: not = `false`
    override type not      = `false`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isTrue
    override type ===[that <: Boolean]                        = that#isTrue

    override  def &&[that <: Boolean](that: that): &&[that] = that
    override type &&[that <: Boolean] = that

    override  def ||[that <: Boolean](that: that): ||[that] = `true`
    override type ||[that <: Boolean]                       = `true`

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = then
    override type `if`[then <: Function0, _else <: Function0]                                              = then

    override def undual: undual = true

    override private[mada]  def isTrue: isTrue = `true`
    override private[mada] type isTrue         = `true`

    override private[mada]  def isFalse: isFalse = `false`
    override private[mada] type isFalse          = `false`
}


/**
 * The false
 */
sealed abstract class `false` extends AbstractBoolean {
    type self = `false`

    override  def not: not = `true`
    override type not      = `true`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isFalse
    override type ===[that <: Boolean]                        = that#isFalse

    override  def &&[that <: Boolean](that: that): &&[that] = `false`
    override type &&[that <: Boolean]                       = `false`

    override  def ||[that <: Boolean](that: that): ||[that] = that
    override type ||[that <: Boolean]                       = that

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = _else
    override type `if`[then <: Function0, _else <: Function0]                                              = _else

    override def undual: undual = false

    override private[mada]  def isTrue: isTrue = `false`
    override private[mada] type isTrue         = `false`

    override private[mada]  def isFalse: isFalse = `true`
    override private[mada] type isFalse          = `true`
}


private[mada] object _Boolean {
    val `true` = new `true`{}
    val `false` = new `false`{}

//     val typeid = nat.dense.Literal._15
//    type typeid = nat.dense.Literal._15
}
