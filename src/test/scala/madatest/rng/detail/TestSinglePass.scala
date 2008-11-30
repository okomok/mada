
package madatest.rng.detail


import mada.Implies._
import mada.rng._
import mada.rng.From._
import mada.rng.Pointer._
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
            assertEquals(from(expected).eval, p <=< q) // assertEquals is a multi-pass algorithm.
        else
            TestSinglePassEquality(from(expected).eval, p <=< q)
    }
}


object TestSinglePassEquality {
    def apply[A](expected: Rng[A], actual: Rng[A]) {
        val (p, q) = actual.toPair
        val (ep, eq) = expected.toPair
        while (p != q && ep != eq) {
            assertEquals("elements not equal", *(p), *(ep))
            ++(p); ++(ep)
        }

        assertEquals("actual rng is shorter than expected.", ep, eq);
        assertEquals("actual rng is longer than expected.", p, q);
    }
}


class SiglePassEqualityTest {
    def testTrivial {
        val r1 = from(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23)).eval
        val r2 = from(Array(0, 0, 4, 4, 6, 8,11,12,13,14,15,17,18,19,23)).eval
        assertNotSame(r1, r2)
        TestSinglePassEquality(r1, r2)
    }
}
