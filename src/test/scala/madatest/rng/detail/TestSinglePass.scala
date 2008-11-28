
package madatest.rng.detail


import mada.rng._
import mada.rng.From._
import junit.framework.Assert._


object TestSinglePass {
    def apply[A](expected: Array[A], actual: Rng[A]) {
        AssertModels(actual, SinglePassTraversal)

        val (p, q) = actual.toPair
        assertEquals(p, p);
        assertEquals(q, q);
        AssertImplies(!(p == q), p != q)
        AssertImplies(!(p != q), p == q)

        // assertEquals requires ForwardRng.
        assertTrue("unexpected rng", from(expected).eval == (p <=< q))
    }
}
