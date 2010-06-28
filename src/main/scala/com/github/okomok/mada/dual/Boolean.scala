

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Boolean
 */
sealed trait Boolean extends Operatable_=== with Operatable_&& with Operatable_|| {
    final override type Operand_=== = Boolean
    final override type Operand_&& = Boolean
    final override type Operand_|| = Boolean

    def not: not
    type not <: Boolean

    private[mada] def isTrue: isTrue
    private[mada] type isTrue <: Boolean

    private[mada] def isFalse: isFalse
    private[mada] type isFalse <: Boolean

    private[mada] def if_Any[then <: Any, _else <: Any](then: then, _else: _else): if_Any[then, _else]
    private[mada] type if_Any[then <: Any, _else <: Any] <: Any

    private[mada] def if_Boolean[then <: Boolean, _else <: Boolean](then: then, _else: _else): if_Boolean[then, _else]
    private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] <: Boolean

    private[mada] def if_Nat[then <: Nat, _else <: Nat](then: then, _else: _else): if_Nat[then, _else]
    private[mada] type if_Nat[then <: Nat, _else <: Nat] <: Nat
}

/**
 * The dual true
 */
sealed trait `true` extends Boolean {
    override def &&[that <: Boolean](that: that): &&[that] = that
    override type &&[that <: Boolean] = that

    override def ||[that <: Boolean](that: that): ||[that] = `true`
    override type ||[that <: Boolean] = `true`

    override def not: not = `false`
    override type not = `false`

    override def ===[that <: Boolean](that: that): ===[that] = that.isTrue
    override type ===[that <: Boolean] = that#isTrue

    override private[mada] def isTrue: isTrue = `true`
    override private[mada] type isTrue = `true`

    override private[mada] def isFalse: isFalse = `false`
    override private[mada] type isFalse = `false`

    override private[mada] def if_Any[then <: Any, _else <: Any](then: then, _else: _else): if_Any[then, _else] = then
    override private[mada] type if_Any[then <: Any, _else <: Any] = then

    override private[mada] def if_Boolean[then <: Boolean, _else <: Boolean](then: then, _else: _else): if_Boolean[then, _else] = then
    override private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] = then

    override private[mada] def if_Nat[then <: Nat, _else <: Nat](then: then, _else: _else): if_Nat[then, _else] = then
    override private[mada] type if_Nat[then <: Nat, _else <: Nat] = then
}

/**
 * The dual false
 */
sealed trait `false` extends Boolean {
    override def &&[that <: Boolean](that: that): &&[that] = `false`
    override type &&[that <: Boolean] = `false`

    override def ||[that <: Boolean](that: that): ||[that] = that
    override type ||[that <: Boolean] = that

    override def not: not = `true`
    override type not = `true`

    override def ===[that <: Boolean](that: that): ===[that] = that.isFalse
    override type ===[that <: Boolean] = that#isFalse

    override private[mada] def isTrue: isTrue = `false`
    override private[mada] type isTrue = `false`

    override private[mada] def isFalse: isFalse = `true`
    override private[mada] type isFalse = `true`

    override private[mada] def if_Any[then <: Any, _else <: Any](then: then, _else: _else): if_Any[then, _else] = _else
    override private[mada] type if_Any[then <: Any, _else <: Any] = _else

    override private[mada] def if_Boolean[then <: Boolean, _else <: Boolean](then: then, _else: _else): if_Boolean[then, _else] = _else
    override private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] = _else

    override private[mada] def if_Nat[then <: Nat, _else <: Nat](then: then, _else: _else): if_Nat[then, _else] = _else
    override private[mada] type if_Nat[then <: Nat, _else <: Nat] = _else
}


private[mada] object _Boolean { // works around `sealed`
    val _true = new `true`{}
    val _false = new `false`{}
}
