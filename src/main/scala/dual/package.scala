

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual {


// util

    /**
     * The dual throw
     */
     def `throw`[x <: scala.Throwable](x: x): `throw`[x] = throw x
    type `throw`[x <: scala.Throwable] = Nothing

    /**
     * The dual boxing
     */
     def box[x](x: x): box[x] = Box(x)
    type box[x] = Box[x]

    /**
     * Returns corresponding runtime value.
     */
     def unmeta[x <: Any](implicit _unmeta: Unmeta[x]): x = _unmeta.apply


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
     def assertNot[c <: Boolean](c: c): assert[c] = { if (c.undual) throw new java.lang.AssertionError("dual.assertNot"); Unit }
    type assertNot[c <: Boolean] = Unit


// Boolean

    @aliasOf("boolean.Boolean")
     val Boolean = boolean.Boolean
    type Boolean = boolean.Boolean

    @aliasOf("boolean.`true`")
     val `true` = boolean.`true`
    type `true` = boolean.`true`

    @aliasOf("boolean.`false`")
     val `false` = boolean.`false`
    type `false` = boolean.`false`

    @aliasOf("boolean.`if`")
     def `if`[c <: Boolean, then <: Function0, _else <: Function0](c: c, then: then, _else: _else): `if`[c, then, _else] = boolean.`if`(c, then, _else)
    type `if`[c <: Boolean, then <: Function0, _else <: Function0] = boolean.`if`[c, then, _else]


// Either

    @aliasOf("either.Either")
     val Either = either.Either
    type Either = either.Either

    @aliasOf("either.Left")
     val Left = either.Left
    type Left[x <: Any] = either.Left[x]

    @aliasOf("either.Right")
     val Right = either.Right
    type Right[x <: Any] = either.Right[x]


// Function

    @aliasOf("function.Const0")
     val Const0 = function.Const0
    type Const0[x <: Any] = function.Const0[x]

    @aliasOf("function.Throw0")
     val Throw0 = function.Throw0
    type Throw0 = function.Throw0


// Nat

    @aliasOf("nat.Nat")
     val Nat = nat.Nat
    type Nat = nat.Nat


// Map

    @aliasOf("map.Map")
     val Map = map.Map
    type Map = map.Map


// Seq

    @aliasOf("seq.Seq")
     val Seq = seq.Seq
    type Seq = seq.Seq

    @equivalentTo("new seq.Nil{}")
     val Nil = seq.Nil
    type Nil = seq.Nil

    @aliasOf("seq.::")
    type ::[x <: Any, xs <: Seq] = seq.::[x, xs]

    @aliasOf("seq.++")
    type ++[xs <: Any, ys <: Seq] = seq.++[xs, ys]


// Set

    @aliasOf("set.Set")
     val Set = set.Set
    type Set = set.Set


// Option

    @aliasOf("option.Option")
     val Option = option.Option
    type Option = option.Option

    @aliasOf("option.None")
     val None = option.None
    type None = option.None

    @aliasOf("option.Some")
     val Some = option.Some
    type Some[x <: Any] = option.Some[x]


// Equiv

    @aliasOf("equiv.Equiv")
     val Equiv = equiv.Equiv
    type Equiv = equiv.Equiv


// Product

    @aliasOf("Tuple2")
     val Pair = Tuple2
    type Pair[v1 <: Any, v2 <: Any] = Tuple2[v1, v2]


// Ordering

    @aliasOf("ordering.Ordering")
     val Ordering = ordering.Ordering
    type Ordering = ordering.Ordering


// Unit

    @equivalentTo("new Unit{}")
    val Unit: Unit = _Unit.value

}
