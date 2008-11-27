
package madatest.rng.detail


import junit.framework.Assert._
import mada.rng._


object TestEquality {
    def apply[A](expected: Array[A], actual: Rng[A], f: (A, A) => Boolean): Unit =  {
        var (ep, eq) = (0, expected.length)
        val (p, q) = (actual.begin, actual.end)
        while (p != q && ep != eq) {
            if (!f(*(p), expected(ep))) {
                fail("equality test failure -- predicate returns false.");
                return
            }
            ++(p); ep = ep + 1
        }

        assertEquals("equality test failure -- actual range is shorter than expected.", ep, eq);
        assertEquals("equality test failure -- actual range is longer than expected.", p, q);
    }

    def apply[A <% Ordered[A]](expected: Array[A], actual: Rng[A]): Unit = apply(expected, actual, (_: A) == (_: A))
}
