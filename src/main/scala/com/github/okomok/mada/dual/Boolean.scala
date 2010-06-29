

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Boolean
 */
sealed trait Boolean extends Operatable_=== with Operatable_&& with Operatable_|| {
     def not: not
    type not <: Boolean

    final override type Operand_=== = Boolean
    final override type Operand_&& = Boolean
    final override type Operand_|| = Boolean

    private[mada]  def isTrue: isTrue
    private[mada] type isTrue <: Boolean

    private[mada]  def isFalse: isFalse
    private[mada] type isFalse <: Boolean

    private[mada]  def if_Any[then <: Function0_Any, _else <: Function0_Any](then: then, _else: _else): if_Any[then, _else]
    private[mada] type if_Any[then <: Function0_Any, _else <: Function0_Any] <: Function0_Any

    private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else]
    private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] <: Function0_Boolean

    private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else]
    private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] <: Function0_Nat

    def toSBoolean: toSBoolean
    final type toSBoolean = scala.Boolean
}

/**
 * The dual true
 */
sealed trait `true` extends Boolean {
    override  def not: not = `false`
    override type not = `false`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isTrue
    override type ===[that <: Boolean] = that#isTrue

    override  def &&[that <: Boolean](that: that): &&[that] = that
    override type &&[that <: Boolean] = that

    override  def ||[that <: Boolean](that: that): ||[that] = `true`
    override type ||[that <: Boolean] = `true`

    override private[mada]  def isTrue: isTrue = `true`
    override private[mada] type isTrue = `true`

    override private[mada]  def isFalse: isFalse = `false`
    override private[mada] type isFalse = `false`

    override private[mada]  def if_Any[then <: Function0_Any, _else <: Function0_Any](then: then, _else: _else): if_Any[then, _else] = then
    override private[mada] type if_Any[then <: Function0_Any, _else <: Function0_Any] = then

    override private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else] = then
    override private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] = then

    override private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else] = then
    override private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] = then

    override def toSBoolean = true
}

/**
 * The dual false
 */
sealed trait `false` extends Boolean {
    override  def not: not = `true`
    override type not = `true`

    override  def ===[that <: Boolean](that: that): ===[that] = that.isFalse
    override type ===[that <: Boolean] = that#isFalse

    override  def &&[that <: Boolean](that: that): &&[that] = `false`
    override type &&[that <: Boolean] = `false`

    override  def ||[that <: Boolean](that: that): ||[that] = that
    override type ||[that <: Boolean] = that

    override private[mada]  def isTrue: isTrue = `false`
    override private[mada] type isTrue = `false`

    override private[mada]  def isFalse: isFalse = `true`
    override private[mada] type isFalse = `true`

    override private[mada]  def if_Any[then <: Function0_Any, _else <: Function0_Any](then: then, _else: _else): if_Any[then, _else] = _else
    override private[mada] type if_Any[then <: Function0_Any, _else <: Function0_Any] = _else

    override private[mada]  def if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean](then: then, _else: _else): if_Boolean[then, _else] = _else
    override private[mada] type if_Boolean[then <: Function0_Boolean, _else <: Function0_Boolean] = _else

    override private[mada]  def if_Nat[then <: Function0_Nat, _else <: Function0_Nat](then: then, _else: _else): if_Nat[then, _else] = _else
    override private[mada] type if_Nat[then <: Function0_Nat, _else <: Function0_Nat] = _else

    override def toSBoolean = false
}


private[mada] object _Boolean { // works around `sealed`.
    val _true = new `true`{}
    val _false = new `false`{}
}
