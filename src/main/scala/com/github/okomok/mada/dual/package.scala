

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual extends dual.nat.LiteralCommon {


    /**
     * The dual throw
     */
     def `throw`[x <: scala.Throwable](x: x): `throw`[x] = throw x
    type `throw`[x <: scala.Throwable] = Nothing


// assertions

    /**
     * assertion
     */
    @elidable(ASSERTION)
     def assert[c <: Boolean](c: c): assert[c] = { if (!c.undual) throw new java.lang.AssertionError("dual.assert"); Unit }
    type assert[c <: Boolean] = Unit

    /**
     * negative assertion
     */
    @elidable(ASSERTION)
     def assertNot[b <: Boolean](b: b): assert[b] = { if (b.undual) throw new java.lang.AssertionError("dual.assertNot"); Unit }
    type assertNot[b <: Boolean] = Unit


// Boolean

    @equivalentTo("new `true`{}")
    val `true` = Boolean._true

    @equivalentTo("new `false`{}")
    val `false` = Boolean._false

    /**
     * The if-expression to return Any.
     */
     def `if`[b <: Boolean, then <: Function0, _else <: Function0](b: b, then: then, _else: _else): `if`[b, then, _else] = b.`if`(then, _else)
    type `if`[b <: Boolean, then <: Function0, _else <: Function0] = b#`if`[then, _else]


// Nat

    @equivalentTo("new Zero{}")
    val Zero: Zero = Nat._Zero

    @equivalentTo("new singular{}")
    private[mada] val singular: singular = Nat._singular


// Option

    @equivalentTo("new None{}")
    val None: None = Option._None


// List

    @equivalentTo("new Nil{}")
    val Nil: Nil = List._Nil

    @equivalentTo("t#addFirst[h]")
    type ::[x <: Any, xs <: List] = xs#addFirst[x]

    @equivalentTo("ys#prepend[xs]")
    type :::[xs <: List, ys <: List] = ys#prepend[xs]

    @equivalentTo("ys#reversePrepend[xs]")
    type reverse_:::[xs <: List, ys <: List] = ys#prependReversed[xs]


// Unit

    @equivalentTo("new Unit{}")
    val Unit: Unit = _Unit.value

}
