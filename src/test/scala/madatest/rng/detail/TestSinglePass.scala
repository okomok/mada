
package madatest.rng.detail


import mada.Implies._
import mada.rng._
import mada.rng.From._
import mada.rng.ToArray._
import junit.framework.Assert._


object TestSinglePassReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        AssertModels(actual, SinglePassTraversal)

        val (p, q) = actual.toPair
        assertEquals(p, p);
        assertEquals(q, q);
        assertTrue(!(p == q) implies p != q)
        assertTrue(!(p != q) implies p == q)

        if (actual models ForwardTraversal)
            assertEquals(from(expected).eval, p <=< q)
        else
            assertEquals(from(expected).eval, (p <=< q).toExpr.rng_toArray.eval)
    }
}
