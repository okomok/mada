

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._


//import junit.framework.Assert._

//import boolean.Operator._


class AssertTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        mada.dual.assert(`true`)
        try {
            mada.dual.assert(`false`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }

    def testTrivial2 {
        mada.dual.assert(`true` nequal `false`)
        try {
            mada.dual.assert(`true` equal `false`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }
    def testNotTrivial {
        assertNot(`false`)
        try {
            assertNot(`true`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }

    def testNotTrivial2 {
        assertNot(`true` equal `false`)
        try {
            assertNot(`true` nequal `false`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }

    trait testMeta {
        free.assert[`true`]
        free.assertNot[`false`]
        free.assert[`true`# equal [`true`]]
        free.assertNot[`true`# nequal [`true`]]
    }

}
