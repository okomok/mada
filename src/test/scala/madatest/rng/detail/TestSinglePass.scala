
package madatest.rng.detail


import mada.Implies._
import mada.rng._
import mada.rng.From._
import junit.framework.Assert._


object TestSinglePass {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        AssertModels(actual, SinglePassTraversal)

        val (p, q) = actual.toPair
        assertEquals(p, p);
        assertEquals(q, q);
        assertTrue(!(p == q) implies p != q)
        assertTrue(!(p != q) implies p == q)

        // assertEquals requires ForwardRng.
        assertTrue("unexpected rng", from(expected).eval == (p <=< q))
    }
}
