

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class BooleanTest {
    def testTrivial: Unit = {
        val tr = traversable.fromValues(2,4,6)
        val t = tr.start
        assertTrue(t)
        assertFalse(!t)

        // SLS is weird.
        assertTrue(t && t)
        assertTrue(t || neverCall)
        assertTrue(t || t)

        if (t) {
        } else if (t && t) {
        } else if (t || t) {
        } else if (t) {
        } else {
            fail("doh")
        }

        while (t) {
            t.++
        }

        assertTrue(!t)
        assertFalse(t)
        assertFalse(t && neverCall)

        while (!t) {
            return
        }
    }

    def neverCall: Boolean = {
        fail("not short-circuit")
        throw new Error
    }
}
