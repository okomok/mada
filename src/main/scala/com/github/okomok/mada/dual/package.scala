

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual {


    /**
     * The dual throw
     */
     def `throw`[x <: scala.Throwable](x: x): `throw`[x] = throw x
    type `throw`[x <: scala.Throwable] = Nothing


// util
    /**
     * The dual constant function
     */
     def always0[x <: Any](x: x): Always0[x] = Always0(x)
    type always0[x <: Any] = Always0[x]

    /**
     * The dual lazy function
     */
     def byLazy[x <: Any](x: => x): byLazy[x] = new ByLazy(x)
    type byLazy[x <: Any] = ByLazy[x]



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
    val `true` = _Boolean.`true`

    @equivalentTo("new `false`{}")
    val `false` = _Boolean.`false`

    /**
     * The if-expression to return Any.
     */
     def `if`[b <: Boolean, then <: Function0, _else <: Function0](b: b, then: then, _else: _else): `if`[b, then, _else] = b.`if`(then, _else)
    type `if`[b <: Boolean, then <: Function0, _else <: Function0] = b#`if`[then, _else]


// Option

    @equivalentTo("new None{}")
    val None: None = _Option.None


// List

    @equivalentTo("new Nil{}")
    val Nil: Nil = _List.Nil

    @equivalentTo("x#addFirst[xs]")
    type ::[x <: Any, xs <: List] = xs#addFirst[x]

    @equivalentTo("ys#prepend[xs]")
    type :::[xs <: List, ys <: List] = ys#prepend[xs]

    @equivalentTo("ys#reversePrepend[xs]")
    type reverse_:::[xs <: List, ys <: List] = ys#prependReversed[xs]


// Unit

    @equivalentTo("new Unit{}")
    val Unit: Unit = _Unit.value

}
