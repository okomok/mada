

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.util.implies
import junit.framework.Assert._


class BooleansTest {
    def neverEvaluated: Boolean = {
        fail("impossible")
        false
    }

    def testMe: Unit = {
        assertTrue(implies(true, true))
        assertTrue(implies(false, true))
        assertTrue(implies(false, false))
        assertFalse(implies(true, false))
        implies(false, neverEvaluated)
    }
}
