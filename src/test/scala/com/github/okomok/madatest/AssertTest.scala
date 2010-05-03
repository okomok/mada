

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import mada.util.assert
import mada.util
import junit.framework.Assert._
import junit.framework.TestCase


class AssertTest extends TestCase {
    def neverEvaluated(): Boolean = {
        assertTrue("impossible", false)
        false
    }

    def neverEvaluatedMsg(): String = {
        fail("doh")
        "doh"
    }

    def testTrivial: Unit = {
        mada.blend.doIf[mada.isDebug] {
            assertTrue(mada.isDebug)
            var thrown = false
            try {
                util.assert("fail", false)
            } catch {
                case e: java.lang.AssertionError => thrown = true
            }
            assertTrue("must be thrown", thrown)
        }
    }

    def mustBeEvaluated(): Boolean = {
        assertTrue("possible", false)
        true
    }

    def testVerify {
        var thrown = false
        try {
            util.verify("fail", mustBeEvaluated)
        } catch {
            case e: junit.framework.AssertionFailedError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }

    def testNotEvaluatedMsg: Unit = {
        util.assert(neverEvaluatedMsg, true)
        util.verify(neverEvaluatedMsg, true)
    }

    def testCheck: Unit = {
        mada.blend.doIf[mada.isDebug] {
            assertTrue(mada.isDebug)
            assertEquals(12, util.ensure(12)(10 < (_: Int)))

            var thrown = false
            try {
                util.ensure("fail", 12)(10 > (_: Int))
            } catch {
                case e: java.lang.AssertionError => thrown = true
            }
            assertTrue("must be thrown", thrown)
        }
    }
}
