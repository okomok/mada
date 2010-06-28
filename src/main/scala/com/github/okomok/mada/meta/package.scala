

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object meta
    extends meta.nat.LiteralCommon with meta.OperatorCommon {


    @aliasOf("Nothing")
    type `null` = Nothing


// unmeta

    /**
     * Returns corresponding runtime value.
     */
    def unmeta[From, To](implicit _unmeta: Unmeta[From, To]): To = _unmeta()


// assertions

    // Prefer methods to case classes:
    //   case classes often permit should-be-illegal expression.

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


// if

    /**
     * The if-expression to return Any.
     */
    type if_Any[cond <: Boolean, then <: Any, _else <: Any] = cond#if_Any[then, _else]

    /**
     * The if-expression to return Boolean.
     */
    type if_Boolean[cond <: Boolean, then <: Boolean, _else <: Boolean] = cond#if_Boolean[then, _else]

    /**
     * The if-expression to return Nat.
     */
    type if_Nat[cond <: Boolean, then <: Nat, _else <: Nat] = cond#if_Nat[then, _else]


// functional

    @aliasOf("tuple2")
    type pair[v1, v2] = tuple2[v1, v2]

    @equivalentTo("a")
    type identity[a] = a

    @equivalentTo("a")
    type project1st[a, b] = a

    @equivalentTo("b")
    type project2nd[a, b] = b

    @equivalentTo("p#_1")
    type select1st[p <: Product2] = p#_1

    @equivalentTo("p#_2")
    type select2nd[p <: Product2] = p#_2

}
