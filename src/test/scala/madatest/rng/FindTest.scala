
package madatest.rng


import mada.Expr
import mada.rng._
import mada.ExprConversions._
import mada.rng.FindPointerOf._
import mada.rng.Find._
import mada.rng.Exists._
import mada.rng.Forall._
import junit.framework.Assert._


class FindTest {
    def testFind {
        val r = Expr(Interval(2, 100))
        assertEquals(r.find((_: Int) == 30).eval.get, 30)
        assertEquals(*(r.findPointerOf((_: Int) == 30).eval), 30)
    }

    def testExists {
        val r = Expr(Interval(2, 100))
        assertTrue(r.exists((_: Int) == 30).eval)
        assertFalse(r.exists((_: Int) == 200).eval)
    }

    def testForall {
        val r = Expr(Interval(2, 100))
        assertTrue(r.forall((_: Int) < 300).eval)
    }
}
