

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object dual
    extends dual.nat.LiteralCommon with dual.OperatorCommon {


    /**
     * Designates an error.
     */
    @companionMethod
     def error: error = throw new Error("dual.error")
    type error = Nothing


// assertions

    // Prefer methods to case classes:
    //   case classes doesn't work well.

    /**
     * assertion
     */
    def assert[a >: `true` <: `true`]: scala.Unit = ()

    /**
     * negative assertion
     */
    def assertNot[a >: `false` <: `false`]: scala.Unit = ()

    /**
     * assertion of identity equality
     */
    def assertSame[a >: b <: b, b]: scala.Unit = ()

    /**
     * assertion if <code>a</code> is lower than <code>b</code>.
     */
    def assertConforms[a <: b, b]: scala.Unit = ()


// Boolean

    @equivalentTo("new `true`{}")
    val `true` = _Boolean._true

    @equivalentTo("new `false`{}")
    val `false` = _Boolean._false

    /**
     * The if-expression to return Any.
     */
    @companionMethod
     def if_Any[cond <: Boolean, then <: Any, _else <: Any](cond: cond, then: then, _else: _else): if_Any[cond, then, _else] = cond.if_Any(then, _else)
    type if_Any[cond <: Boolean, then <: Any, _else <: Any] = cond#if_Any[then, _else]

    /**
     * The if-expression to return Boolean.
     */
    @companionMethod
     def if_Boolean[cond <: Boolean, then <: Boolean, _else <: Boolean](cond: cond, then: then, _else: _else): if_Boolean[cond, then, _else] = cond.if_Boolean(then, _else)
    type if_Boolean[cond <: Boolean, then <: Boolean, _else <: Boolean] = cond#if_Boolean[then, _else]

    /**
     * The if-expression to return Nat.
     */
    @companionMethod
     def if_Nat[cond <: Boolean, then <: Nat, _else <: Nat](cond: cond, then: then, _else: _else): if_Nat[cond, then, _else] = cond.if_Nat(then, _else)
    type if_Nat[cond <: Boolean, then <: Nat, _else <: Nat] = cond#if_Nat[then, _else]


// Nat

    @equivalentTo("new zero{}")
    val zero: zero = _Nat._zero

    @equivalentTo("new succ(n)")
    def succ[n <: Nat](n: n): succ[n] = new succ(n)

    @equivalentTo("new singular{}")
    private[mada] val singular: singular = _Nat._singular

}
