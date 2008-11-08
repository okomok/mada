
package madatest.range


import mada.range._
import junit.framework.Assert._


class FindTest {
    def testFind {
        val r = Interval(2, 100)
        assertEquals(r.find(_ == 30).get, 30)
        assertEquals(*(r.findPointerOf(_ == 30)), 30)
    }

    def testExists {
        val r = Interval(2, 100)
        assertTrue(r.exists(_ == 30))
        assertFalse(r.exists(_ == 200))
    }

    def testForall {
        val r = Interval(2, 100)
        assertTrue(r.forall(_ < 300))
    }
}
