

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._


import junit.framework.Assert._

//import boolean.Operator._


class AssertTest extends junit.framework.TestCase {

    def testTrivial {
        assert(`true`)
        try {
            assert(`false`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }

    def testTrivial2 {
        assert(`true` !== `false`)
        try {
            assert(`true` === `false`)
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
        assertNot(`true` === `false`)
        try {
            assertNot(`true` !== `false`)
            fail("never come here")
        } catch {
            case _: AssertionError =>
        }
    }

    trait testMeta {
        meta.assert[`true`]
        meta.assertNot[`false`]
        meta.assert[`true`# === [`true`]]
        meta.assertNot[`true`# !== [`true`]]
    }

}
