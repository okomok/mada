
package madatest


import mada.Assert
import mada.Verify
import mada.NDebug
import junit.framework.Assert._
import junit.framework.TestCase


class AssertTest extends TestCase {
    override def setUp {
        NDebug.value = false
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
        assertTrue(!NDebug.value)

        NDebug.value = true
        assertTrue(NDebug.value)
        Assert("never evaluated", neverEvaluated())

        NDebug.value = false
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
            Verify("fail", mustBeEvaluated)
        } catch {
            case e: junit.framework.AssertionFailedError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }

    def testNotEvaluatedMsg: Unit = {
        NDebug.value = true
        Assert(neverEvaluatedMsg, true)
        Verify(neverEvaluatedMsg, true)
        NDebug.value = false
        Assert(neverEvaluatedMsg, true)
        Verify(neverEvaluatedMsg, true)
    }
}
