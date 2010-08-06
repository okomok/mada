

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual {


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
     def `if`[c <: Boolean, then <: Function0, _else <: Function0](c: c, then: then, _else: _else): `if`[c, then, _else] =
        boolean.`if`(c, then, _else)
    type `if`[c <: Boolean, then <: Function0, _else <: Function0] =
        boolean.`if`[c, then, _else]


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

    @aliasOf("function.const0")
     def const0[v <: Any](v: => v): const0[v] = function.const0(v)
    type const0[v <: Any]                     = function.const0[v]

    @aliasOf("function.throw0")
     def throw0(x: Throwable) = function.throw0(x)
    type throw0[_]            = function.throw0[_]


// List

    @aliasOf("list.List")
     val List = list.List
    type List = list.List

    @aliasOf("list.Nil")
     val Nil = list.Nil
    type Nil = list.Nil

    @aliasOf("list.::")
    type ::[x <: Any, xs <: List] = list.::[x, xs]

    @aliasOf("list.++")
    type ++[xs <: List, ys <: List] = list.++[xs, ys]


// Nat

    @aliasOf("nat.Nat")
     val Nat = nat.Nat
    type Nat = nat.Nat


// Map

    @aliasOf("map.Map")
     val Map = map.Map
    type Map = map.Map


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


// assertions

    /**
     * assertion (metamethod is not implemented yet.)
     */
    @elidable(ASSERTION)
     def assert[c <: Boolean](c: c): Unit = {
         if (!c.undual) throw new java.lang.AssertionError("dual.assert")
         Unit
    }
    // type assert[c <: Boolean] = How?

    /**
     * negative assertion (metamethod is not implemented yet.)
     */
    @elidable(ASSERTION)
     def assertNot[c <: Boolean](c: c): Unit = assert(c.not)
    // type assertNot[c <: Boolean]                  = assert[c#not]


// util

    /**
     * Returns corresponding runtime value.
     */
     def unmeta[x <: Any](implicit _unmeta: Unmeta[x]): x = _unmeta.apply

}
