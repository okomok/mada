

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual


/**
 * The dual Boolean
 */
sealed trait Boolean extends Operatable {
    def and[that <: Boolean](that: that): and[that]
    type and[that <: Boolean] <: Boolean

    def or[that <: Boolean](that: that): or[that]
    type or[that <: Boolean] <: Boolean

    def not: not
    type not <: Boolean

    def equals[that <: Boolean](that: that): equals[that]
    type equals[that <: Boolean] <: Boolean

    override type Operand_== = Boolean
    override type operator_==[that <: Boolean] = equals[that]

    override type Operand_&& = Boolean
    override type operator_&&[that <: Boolean] = and[that]
    override type Operand_|| = Boolean
    override type operator_||[that <: Boolean] = or[that]

    private[mada] type isTrue <: Boolean
    private[mada] type isFalse <: Boolean

    private[mada] type if_Any[then <: Any, _else <: Any] <: Any
    private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] <: Boolean
    private[mada] type if_Nat[then <: Nat, _else <: Nat] <: Nat
}

/**
 * The dual true
 */
sealed trait `true` extends Boolean {
    override def and[that <: Boolean](that: that): and[that] = that
    override type and[that <: Boolean] = that

    override def or[that <: Boolean](that: that): or[that] = `true`
    override type or[that <: Boolean] = `true`

    override def not: not = `false`
    override type not = `false`

    override def equals[that <: Boolean](that: that) = that.isTrue
    override type equals[that <: Boolean] = that#isTrue

    override private[mada] def isTrue = `true`
    override private[mada] type isTrue = `true`

    override private[mada] def isFalse = `false`
    override private[mada] type isFalse = `false`

    override private[mada] type if_Any[then <: Any, _else <: Any] = then
    override private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] = then
    override private[mada] type if_Nat[then <: Nat, _else <: Nat] = then
}

/**
 * The dual false
 */
sealed trait `false` extends Boolean {
    override type and[that <: Boolean] = `false`
    override type or[that <: Boolean] = that
    override type not = `true`
    override type equals[that <: Boolean] = that#isFalse

    override private[mada] def isTrue = `false`
    override private[mada] type isTrue = `false`

    override private[mada] def isFalse = `true`
    override private[mada] type isFalse = `true`

    override private[mada] type if_Any[then <: Any, _else <: Any] = _else
    override private[mada] type if_Boolean[then <: Boolean, _else <: Boolean] = _else
    override private[mada] type if_Nat[then <: Nat, _else <: Nat] = _else
}


private[mada] object _Boolean { // works around `sealed`
    val _true = new `true`
    val _false = new `false`
}
