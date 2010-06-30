

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._
//import junit.framework.Assert._


class BooleanTest extends junit.framework.TestCase {
    def testUnmeta: Unit = {
        import junit.framework.Assert._
        assertEquals(true, `true`.toSBoolean)
        assertEquals(false, `false`.toSBoolean)
    }

    trait testTrivial {
        meta.assertSame[scala.Boolean, `true`#toSBoolean]
        meta.assertSame[`true`, `true`]
     //   meta.assert[`false` === if_Boolean[`true`, `false`, `true`]]
     //   meta.assert[`false` === if_Boolean[`false`, `true`, `false`]]

    //    meta.assertSame[`false`, if_Boolean[`true`, `false`, `true`]]
    //    meta.assertSame[`false`, if_Boolean[`false`, `true`, `false`]]
    }

    meta.assert[`true`]
    meta.assertNot[`false`]

    meta.assert[`true` === `true`]
    meta.assert[`false` === `false`]
    meta.assert[`true` !== `false`]
    meta.assert[`false` !== `true`]

    type myNot[b <: Boolean] = b#not
    meta.assert[myNot[`true`] !== `true`]
    meta.assert[myNot[`false`] !== `false`]
    meta.assert[myNot[`true`] === `false`]
    meta.assert[myNot[`false`] === `true`]

    trait testOperator {
        meta.assert[`true` && `true`]
        meta.assert[(`false` && `true`)#not]
        meta.assert[`false` || `true`]
        meta.assert[`true` || `false`]
    }

    trait testPropagation {
        type incinc[n <: Nat] = if_Nat[n === _3N, Inc_Nat[n], Always_Nat[n]]#apply#increment
        meta.assertConforms[incinc[_2N], Nat]

        meta.assert[if_Nat[_2N === _3N, Inc_Nat[_2N], Always_Nat[_2N]]#apply#increment === _3N]
        meta.assert[incinc[_2N] === _3N]
        meta.assert[incinc[_3N] === _5N]
    }

    class Always_Nat[e <: Nat](e: e) extends Function0_Nat {
        override def apply = e
        override type apply = e
    }
    class Inc_Nat[e <: Nat](val e: e) extends Function0_Nat {
        override def apply = e.increment
        override type apply = e#increment
    }
}
