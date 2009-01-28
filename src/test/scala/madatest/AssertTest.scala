

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Assert
import junit.framework.Assert._
import junit.framework.TestCase


/*
class AssertTest extends TestCase {
    override def setUp {
        Assert.isEnabled = true
    }

    def neverEvaluated(): Boolean = {
        assertTrue("impossible", false)
        false
    }

    def neverEvaluatedMsg(): String = {
        fail("doh")
        "doh"
    }

    def testTrivial {
        assertTrue(Assert.isEnabled)

        Assert.isEnabled = false
        assertFalse(Assert.isEnabled)
        Assert("never evaluated", neverEvaluated())

        Assert.isEnabled = true
        var thrown = false
        try {
            Assert("fail", false)
        } catch {
            case e: java.lang.AssertionError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }

    def mustBeEvaluated(): Boolean = {
        assertTrue("possible", false)
        true
    }

    def testVerify {
        var thrown = false
        try {
            Assert.verify("fail", mustBeEvaluated)
        } catch {
            case e: junit.framework.AssertionFailedError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }

    def testNotEvaluatedMsg: Unit = {
        Assert.isEnabled = false
        Assert(neverEvaluatedMsg, true)
        Assert.verify(neverEvaluatedMsg, true)
        Assert.isEnabled = true
        Assert(neverEvaluatedMsg, true)
        Assert.verify(neverEvaluatedMsg, true)
    }

    def testCheck: Unit = {
        assertEquals(12, Assert.check(10 < (_: Int), 12))

        var thrown = false
        try {
            Assert.check("fail", 10 > (_: Int), 12)
        } catch {
            case e: java.lang.AssertionError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }
}
*/

