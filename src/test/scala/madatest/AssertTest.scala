

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Assert
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
        import mada.Blend.DoIf._
        mada.Blend.doIf[mada.Env.isDebug] {
            assertTrue(mada.Env.isDebug)
            var thrown = false
            try {
                Assert("fail", false)
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
            Assert.verify("fail", mustBeEvaluated)
        } catch {
            case e: junit.framework.AssertionFailedError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }

    def testNotEvaluatedMsg: Unit = {
        Assert(neverEvaluatedMsg, true)
        Assert.verify(neverEvaluatedMsg, true)
    }

    def testCheck: Unit = {
        import mada.Blend.DoIf._
        mada.Blend.doIf[mada.Env.isDebug] {
            assertTrue(mada.Env.isDebug)
            assertEquals(12, Assert.ensure(12)(10 < (_: Int)))

            var thrown = false
            try {
                Assert.ensure("fail", 12)(10 > (_: Int))
            } catch {
                case e: java.lang.AssertionError => thrown = true
            }
            assertTrue("must be thrown", thrown)
        }
    }
}
