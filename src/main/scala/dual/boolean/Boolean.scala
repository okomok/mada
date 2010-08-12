

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

     def equal[that <: Boolean](that: that): equal[that]
    type equal[that <: Boolean] <: Boolean

     def nequal[that <: Boolean](that: that): nequal[that]
    type nequal[that <: Boolean] <: Boolean

     def and[that <: Boolean](that: that): and[that]
    type and[that <: Boolean] <: Boolean

     def or[that <: Boolean](that: that): or[that]
    type or[that <: Boolean] <: Boolean

     def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else]
    type `if`[then <: Function0, _else <: Function0] <: Function0

    private[dual]  def isTrue: isTrue
    private[dual] type isTrue <: Boolean

    private[dual]  def isFalse: isFalse
    private[dual] type isFalse <: Boolean

    final override type undual = scala.Boolean
    final override  def canEqual(that: scala.Any) = that.isInstanceOf[Boolean]
}


private[dual]
sealed abstract class AbstractBoolean extends Boolean {
    final override  def nequal[that <: Boolean](that: that): nequal[that] = equal(that).not
    final override type nequal[that <: Boolean]                           = equal[that]#not

    final override  def naturalOrdering: naturalOrdering = boolean.naturalOrdering
    final override type naturalOrdering                  = boolean.naturalOrdering

//    final override protected  def typeid = _Boolean.typeid
//    final override protected type typeid = _Boolean.typeid
}


/**
 * The dual true
 */
sealed abstract class `true` extends AbstractBoolean {
    type self = `true`

    override  def not: not = `false`
    override type not      = `false`

    override  def equal[that <: Boolean](that: that): equal[that] = that.isTrue
    override type equal[that <: Boolean]                          = that#isTrue

    override  def and[that <: Boolean](that: that): and[that] = that
    override type and[that <: Boolean]                        = that

    override  def or[that <: Boolean](that: that): or[that] = `true`
    override type or[that <: Boolean]                       = `true`

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = then
    override type `if`[then <: Function0, _else <: Function0]                                              = then

    override def undual: undual = true

    override private[dual]  def isTrue: isTrue = `true`
    override private[dual] type isTrue         = `true`

    override private[dual]  def isFalse: isFalse = `false`
    override private[dual] type isFalse          = `false`
}


/**
 * The dual false
 */
sealed abstract class `false` extends AbstractBoolean {
    type self = `false`

    override  def not: not = `true`
    override type not      = `true`

    override  def equal[that <: Boolean](that: that): equal[that] = that.isFalse
    override type equal[that <: Boolean]                          = that#isFalse

    override  def and[that <: Boolean](that: that): and[that] = `false`
    override type and[that <: Boolean]                        = `false`

    override  def or[that <: Boolean](that: that): or[that] = that
    override type or[that <: Boolean]                       = that

    override  def `if`[then <: Function0, _else <: Function0](then: then, _else: _else): `if`[then, _else] = _else
    override type `if`[then <: Function0, _else <: Function0]                                              = _else

    override def undual: undual = false

    override private[dual]  def isTrue: isTrue = `false`
    override private[dual] type isTrue         = `false`

    override private[dual]  def isFalse: isFalse = `true`
    override private[dual] type isFalse          = `true`
}


private[dual]
object _Boolean {
    val `true` = new `true`{}
    val `false` = new `false`{}

//     val typeid = nat.dense.Literal._15
//    type typeid = nat.dense.Literal._15
}
