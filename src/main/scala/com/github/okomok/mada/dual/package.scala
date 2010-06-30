

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual
    extends dual.nat.LiteralCommon with dual.OperatorCommon {


    /**
     * Designates an unsupported operation error.
     */
     def unsupported: unsupported = throw new java.lang.UnsupportedOperationException
    type unsupported = Nothing


// assertions

    /**
     * assertion
     */
    @elidable(ASSERTION)
     def assert[a <: Boolean](a: a): assert[a] = if (!a.toSBoolean) throw new java.lang.AssertionError("dual.assert")
    type assert[a <: Boolean] = Unit

    /**
     * negative assertion
     */
    @elidable(ASSERTION)
     def assertNot[a <: Boolean](a: a): assert[a] = if (a.toSBoolean) throw new java.lang.AssertionError("dual.assertNot")
    type assertNot[a <: Boolean] = Unit


// Boolean

    @equivalentTo("new `true`{}")
    val `true` = _Boolean._true

    @equivalentTo("new `false`{}")
    val `false` = _Boolean._false

    /**
     * The if-expression to return Any.
     */
     def if_Any[cond <: Boolean, then <: Function0_Any, _else <: Function0_Any](cond: cond, then: then, _else: _else): if_Any[cond, then, _else] = cond.if_Any(then, _else)
    type if_Any[cond <: Boolean, then <: Function0_Any, _else <: Function0_Any] = cond#if_Any[then, _else]

    /**
     * The if-expression to return Boolean.
     */
     def if_Boolean[cond <: Boolean, then <: Function0_Boolean, _else <: Function0_Boolean](cond: cond, then: then, _else: _else): if_Boolean[cond, then, _else] = cond.if_Boolean(then, _else)
    type if_Boolean[cond <: Boolean, then <: Function0_Boolean, _else <: Function0_Boolean] = cond#if_Boolean[then, _else]

    /**
     * The if-expression to return Nat.
     */
     def if_Nat[cond <: Boolean, then <: Function0_Nat, _else <: Function0_Nat](cond: cond, then: then, _else: _else): if_Nat[cond, then, _else] = cond.if_Nat(then, _else)
    type if_Nat[cond <: Boolean, then <: Function0_Nat, _else <: Function0_Nat] = cond#if_Nat[then, _else]


// Nat

    @equivalentTo("new Zero{}")
    val Zero: Zero = _Nat._Zero

    @equivalentTo("new singular{}")
    private[mada] val singular: singular = _Nat._singular


// Unit

    @aliasOf("scala.Unit")
    type Unit = scala.Unit

    @aliasOf("scala.Unit")
     val unit: unit = ()
    type unit = Unit

}
