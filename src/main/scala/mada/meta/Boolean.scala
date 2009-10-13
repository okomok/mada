

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package meta


/**
 * The meta Boolean
 */
sealed trait Boolean extends Operatable {
    type and[that <: Boolean] <: Boolean
    type or[that <: Boolean] <: Boolean
    type not <: Boolean
    type equals[that <: Boolean] <: Boolean

    override type Operand_== = Boolean
    override type operator_==[that <: Boolean] = equals[that]

    override type Operand_&& = Boolean
    override type operator_&&[that <: Boolean] = and[that]
    override type Operand_|| = Boolean
    override type operator_||[that <: Boolean] = or[that]

    private[mada] type isTrue <: Boolean
    private[mada] type isFalse <: Boolean

    private[mada] type _if[then, _else]
    // private[mada] type _if_[then, _else <: then#This] <: then#This // doesn't work in dependent context.
    // private[mada] type ifThen[then] <: then // doesn't work, too.
    private[mada] type natIf[then <: Nat, _else <: Nat] <: Nat
}

/**
 * The meta true
 */
sealed trait `true` extends Boolean {
    override type and[that <: Boolean] = that
    override type or[that <: Boolean] = `true`
    override type not = `false`
    override type equals[that <: Boolean] = that#isTrue

    override private[mada] type isTrue = `true`
    override private[mada] type isFalse = `false`

    override private[mada] type _if[then, _else] <: then
    // override private[mada] type _if_[then, _else <: then#This] = then#`this`
    // override private[mada] type ifThen[then] = then
    override private[mada] type natIf[then <: Nat, _else <: Nat] = then
}

/**
 * The meta false
 */
sealed trait `false` extends Boolean {
    override type and[that <: Boolean] = `false`
    override type or[that <: Boolean] = that
    override type not = `true`
    override type equals[that <: Boolean] = that#isFalse

    override private[mada] type isTrue = `false`
    override private[mada] type isFalse = `true`

    override private[mada] type _if[then, _else] <: _else
    // override private[mada] type _if_[then, _else <: then#This] = _else
    // override private[mada] type ifThen[then] = Nothing
    override private[mada] type natIf[then <: Nat, _else <: Nat] = _else
}
