
package madatest


import mada.Implies._
import junit.framework.Assert._


class ImpliesTest {
    def neverEvaluated: Boolean = {
        fail("impossible")
        false
    }

    def testMe {
        assertTrue(true implies true)
        assertTrue(false implies true)
        assertTrue(false implies false)
        assertFalse(true implies false)
        false implies neverEvaluated
    }
}
