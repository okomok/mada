

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._
//import junit.framework.Assert._

import mada.dual.nat.peano.Literal._
import mada.dual.nat.Peano
import boolean.Operator._


class BooleanTest extends org.scalatest.junit.JUnit3Suite {
    def testConvert {
        import junit.framework.Assert._
        assertEquals(true, `true`.undual)
        assertEquals(false, `false`.undual)
        assertEquals(`true`, `true`)
        assertEquals(`false`, `false`)
        AssertNotEquals(`false`, `true`)

    }

    trait testTrivial {
        meta.assertSame[scala.Boolean, `true`#undual]
        meta.assertSame[`true`, `true`]
     //   meta.assert[`false` === if_Boolean[`true`, `false`, `true`]]
     //   meta.assert[`false` === if_Boolean[`false`, `true`, `false`]]

    //    meta.assertSame[`false`, if_Boolean[`true`, `false`, `true`]]
    //    meta.assertSame[`false`, if_Boolean[`false`, `true`, `false`]]
    }

    def testDuality {
        val f: `false`# && [`true`] = `false` && `true`
        val t: `false`# || [`true`] = `false` || `true`
        val x: `false` = f && t
        mada.dual.assert(x === `false`)
    }

    meta.assert[`true`]
    meta.assertNot[`false`]

    meta.assert[`true` === `true`]
    meta.assert[`false` === `false`]
    meta.assert[`true` !== `false`]
    meta.assert[`false` !== `true`]

    type myNot[b <: Boolean] = b#not
    meta.assert[myNot[`true`]# !== [`true`]]
    meta.assert[myNot[`false`]# !== [`false`]]
    meta.assert[myNot[`true`]# === [`false`]]
    meta.assert[myNot[`false`]# === [`true`]]

    trait testOperator {
        meta.assert[`true` && `true`]
        meta.assert[(`false` && `true`)#not]
        meta.assert[`false` || `true`]
        meta.assert[`true` || `false`]
    }

    trait testPropagation {
        type incinc[n <: Peano] = `if`[n# equal[_3], Inc_Nat[n], const0[n]]#apply#asInstanceOfNat#increment#decrement#increment
        meta.assertConforms[incinc[_2], Peano]

        meta.assert[`if`[_2# equal[_3], Inc_Nat[_2], const0[_2]]#apply#increment# equal[_3]]
        meta.assert[incinc[_2]# equal[_3]]
        meta.assert[incinc[_3]# equal[_5]]
    }

    class Inc_Nat[e <: Peano](val e: e) extends Function0 {
        override type self = Inc_Nat[e]
        override def apply = e.increment
        override type apply = e#increment
    }
}
