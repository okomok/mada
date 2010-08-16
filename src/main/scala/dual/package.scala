

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import annotation.elidable
import annotation.elidable.ASSERTION


package object dual {


// Boolean

    @equivalentTo("new `true`{}")
     val `true` = _Boolean.`true`

    @equivalentTo("new `false`{}")
     val `false` = _Boolean.`false`

    @equivalentTo("c.`if`(then, _else)")
     def `if`[c <: Boolean, then <: Function0, _else <: Function0](c: c, then: then, _else: _else): `if`[c, then, _else] = c.`if`(then, _else)
    type `if`[c <: Boolean, then <: Function0, _else <: Function0]                                                       = c#`if`[then, _else]


// Function

    @aliasOf("function.const0")
     def const0[v <: Any](v: => v): const0[v] = function.const0(v)
    type const0[v <: Any]                     = function.const0[v]

    @aliasOf("function.throw0")
     def throw0(x: Throwable) = function.throw0(x)
    type throw0[_]            = function.throw0[_]


// List

    @aliasOf("list.Nil")
     val Nil = list.Nil
    type Nil = list.Nil

    @aliasOf("list.::")
    type ::[x <: Any, xs <: List] = list.::[x, xs]


// Option

    @equivalentTo("new None{}")
     val None = _Option.None


// Product

    @aliasOf("Tuple2")
     val Pair = Tuple2
    type Pair[v1 <: Any, v2 <: Any] = Tuple2[v1, v2]


// Unit

    @equivalentTo("new Unit{}")
    val Unit: Unit = _Unit.value


// assertions

    /**
     * assertion (metamethod is not implemented yet.)
     */
    @elidable(ASSERTION)
     def assert[c <: Boolean](c: c): assert[c] = Assert.apply(c)
    type assert[c <: Boolean]                  = Assert.apply[c]

    @elidable(ASSERTION)
    @equivalentTo("assert(c.not)")
     def assertNot[c <: Boolean](c: c): assertNot[c] = assert(c.not)
    type assertNot[c <: Boolean]                     = assert[c#not]


// util

    /**
     * Returns corresponding runtime value.
     */
     def unmeta[x <: Any](implicit _unmeta: Unmeta[x]): x = _unmeta.apply

}
