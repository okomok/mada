
package madatest


import mada.Assert
import mada.NDebug


class AssertTest extends TestCase {
    def neverEvaluated(): Boolean = {
        assertTrue("impossible", false)
        false
    }

    def applyTest {
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
