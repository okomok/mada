
package madatest


import mada.Implies
import junit.framework.Assert._


class ImpliesTest {
    def neverEvaluated: Boolean = {
        fail("impossible")
        false
    }

    def testTrivial {
        assertTrue(Implies(true, true))
        assertTrue(Implies(false, true))
        assertTrue(Implies(false, false))
        assertFalse(Implies(true, false))
        Implies(false, neverEvaluated)
    }
}
