
package madatest


import mada.Assert
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
}
