
package madatest.rng


import mada.rng._
import mada.Expr
import mada.rng.From._
import mada.rng.ToRng._
import junit.framework.Assert._


class IntervalTest {

    def testTrivial = {
        assertEquals(from(2, 5).eval, from(2, 5).eval)
        assertEquals(from(2L, 5L).eval, from(2L, 5L).eval)
    }

    def testTrivial2 = {
        assertEquals(from(IntInterval(2, 5)).eval, from(2, 5).eval)
        assertEquals(from(LongInterval(2L, 5L)).eval, from(2L, 5L).eval)
    }

    def testToRng = {
        assertEquals(Expr(IntInterval(2, 5)).toRng.eval, from(2, 5).eval)
        assertEquals(Expr(LongInterval(2L, 5L)).toRng.eval, from(2L, 5L).eval)
    }
}
