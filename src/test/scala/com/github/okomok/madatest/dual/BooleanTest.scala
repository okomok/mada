

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
        assertSame[scala.Boolean, `true`#toSBoolean]
        assertSame[`true`, `true`]
     //   assert[`false` === if_Boolean[`true`, `false`, `true`]]
     //   assert[`false` === if_Boolean[`false`, `true`, `false`]]

    //    assertSame[`false`, if_Boolean[`true`, `false`, `true`]]
    //    assertSame[`false`, if_Boolean[`false`, `true`, `false`]]
    }

    assert[`true`]
    assertNot[`false`]

    assert[`true` === `true`]
    assert[`false` === `false`]
    assert[`true` !== `false`]
    assert[`false` !== `true`]

    type myNot[b <: Boolean] = b#not
    assert[myNot[`true`] !== `true`]
    assert[myNot[`false`] !== `false`]
    assert[myNot[`true`] === `false`]
    assert[myNot[`false`] === `true`]

    trait testOperator {
        assert[`true` && `true`]
        assert[(`false` && `true`)#not]
        assert[`false` || `true`]
        assert[`true` || `false`]
    }

    trait testPropagation {
        type incinc[n <: Nat] = if_Nat[n === _3N, Inc_Nat[n], Always_Nat[n]]#apply#increment
        assertConforms[incinc[_2N], Nat]

        assert[if_Nat[_2N === _3N, Inc_Nat[_2N], Always_Nat[_2N]]#apply#increment === _3N]
        assert[incinc[_2N] === _3N]
        assert[incinc[_3N] === _5N]
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
