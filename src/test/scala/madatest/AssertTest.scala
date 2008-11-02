
package madatest


import mada.Assert


class AssertTest extends TestCase {
    def neverEvaluated(): Boolean = {
        assertTrue("impossible", false)
        false
    }

    def applyTest {
        assertTrue(Assert.isEnabled)

        Assert.disable
        Assert(neverEvaluated())

        Assert.enable
        var thrown = false
        try {
            Assert(false)
        } catch {
            case e: java.lang.AssertionError => thrown = true
        }
        assertTrue("must be thrown", thrown)
    }
}
