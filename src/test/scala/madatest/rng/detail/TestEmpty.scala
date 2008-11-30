
package madatest.rng.detail


import mada.rng._;
import mada.rng.From._
import junit.framework.Assert._
import Example._


object TestEmpty {
    def apply[A](r: Rng[A]) {
        val (p, q) = r.toPair

        assertEquals(p, q)
        assertEquals(q, p)

        if (r models ForwardTraversal) {
            val (p1, q1) = (p.copy, q.copy)

            assertNotSame(p1, p)
            assertEquals(p1, p)
            assertEquals(p, p1)

            assertNotSame(q1, q)
            assertEquals(q1, q)
            assertEquals(q, q1)
        }
    }
}


class TestEmptyTest {
    def testTrivial {
        TestEmpty(from(empty1).eval)
    }
}
