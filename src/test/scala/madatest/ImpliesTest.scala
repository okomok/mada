
package madatest


import mada.Implies
import mada.Implies.Operator._
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

    def testInfix {
        assertTrue(true implies true)
        assertTrue(false implies true)
        assertTrue(false implies false)
        assertFalse(true implies false)
        false implies neverEvaluated
    }
}
