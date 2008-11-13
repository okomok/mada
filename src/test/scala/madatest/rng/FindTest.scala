
package madatest.rng


import mada.Expr
import mada.rng._
import mada.rng.FindPointerOf._
import mada.rng.Find._
import junit.framework.Assert._


class FindTest {
    def testFind {
        val r = Expr(Interval(2, 100))
        assertEquals(r.find(_ == 30).eval.get, 30)
        assertEquals(*(r.findPointerOf(_ == 30).eval), 30)
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
