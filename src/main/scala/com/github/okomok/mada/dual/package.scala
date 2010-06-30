

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual extends dual.nat.LiteralCommon {


    /**
     * Designates an unsupported operation error.
     */
     def unsupported: unsupported = throw new java.lang.UnsupportedOperationException
    type unsupported = scala.Nothing


// assertions

    /**
     * assertion
     */
    @elidable(ASSERTION)
     def assert[c <: Boolean](c: c): assert[c] = if (!c.toSBoolean) throw new java.lang.AssertionError("dual.assert")
    type assert[c <: Boolean] = Unit

    /**
     * negative assertion
     */
    @elidable(ASSERTION)
     def assertNot[b <: Boolean](b: b): assert[b] = if (b.toSBoolean) throw new java.lang.AssertionError("dual.assertNot")
    type assertNot[b <: Boolean] = Unit


// Boolean

    @equivalentTo("new `true`{}")
    val `true` = Boolean._true

    @equivalentTo("new `false`{}")
    val `false` = Boolean._false

    /**
     * The if-expression to return Any.
     */
     def if_Any[b <: Boolean, then <: Function0_Any, _else <: Function0_Any](b: b, then: then, _else: _else): if_Any[b, then, _else] = b.if_Any(then, _else)
    type if_Any[b <: Boolean, then <: Function0_Any, _else <: Function0_Any] = b#if_Any[then, _else]

    /**
     * The if-expression to return Boolean.
     */
     def if_Boolean[b <: Boolean, then <: Function0_Boolean, _else <: Function0_Boolean](b: b, then: then, _else: _else): if_Boolean[b, then, _else] = b.if_Boolean(then, _else)
    type if_Boolean[b <: Boolean, then <: Function0_Boolean, _else <: Function0_Boolean] = b#if_Boolean[then, _else]

    /**
     * The if-expression to return Nat.
     */
     def if_Nat[b <: Boolean, then <: Function0_Nat, _else <: Function0_Nat](b: b, then: then, _else: _else): if_Nat[b, then, _else] = b.if_Nat(then, _else)
    type if_Nat[b <: Boolean, then <: Function0_Nat, _else <: Function0_Nat] = b#if_Nat[then, _else]

    /**
     * The if-expression to return List.
     */
     def if_List[b <: Boolean, then <: Function0_List, _else <: Function0_List](b: b, then: then, _else: _else): if_List[b, then, _else] = b.if_List(then, _else)
    type if_List[b <: Boolean, then <: Function0_List, _else <: Function0_List] = b#if_List[then, _else]


// Nat

    @equivalentTo("new Zero{}")
    val Zero: Zero = Nat._Zero

    @equivalentTo("new singular{}")
    private[mada] val singular: singular = Nat._singular


// List

    @equivalentTo("new Nil{}")
    val Nil = List._Nil

    @equivalentTo("t#addFirst[h]")
    type ::[x <: Any, xs <: List] = xs#addFirst[x]

    @equivalentTo("ys#prepend[xs]")
    type :::[xs <: List, ys <: List] = ys#prepend[xs]

    @equivalentTo("ys#reversePrepend[xs]")
    type reverse_:::[xs <: List, ys <: List] = ys#prependReversed[xs]

}
