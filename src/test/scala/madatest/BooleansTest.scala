

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Booleans.Infix._
import junit.framework.Assert._


class BooleansTest {
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