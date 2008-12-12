
package madatest.rng.detail


import mada.Implies._
import mada.rng._
import mada.rng.From._
import mada.rng.Force._
import junit.framework.Assert._


object TestSinglePassReadOnly {
    def apply[A](expected: Array[A], actual: Rng[A]): Unit = {
        assertEquals(Traversal.SinglePass, actual.traversal)
        impl(expected, actual)
    }

    def impl[A](expected: Array[A], actual: Rng[A]): Unit = {
        AssertModels(actual, Traversal.SinglePass)

        val (p, q) = actual.toPair
        assertEquals(p, p);
        assertEquals(q, q);
        assertTrue(!(p == q) implies p != q)
        assertTrue(!(p != q) implies p == q)

        if (actual models Traversal.Forward)
            assertTrue(NetBeansSucks(from(expected).eval, p <=< q))
//            assertEquals(from(expected).eval, p <=< q)
        else
            assertEquals(from(expected).eval, (p <=< q).toExpr.force.eval)
    }
}


object NetBeansSucks {
    def apply[A1, A2](r1: Rng[A1], r2: Rng[A2]): Boolean = {
        import Pointer._
        val (p1, q1) = r1.toPair
        val (p2, q2) = r2.toPair
        while (p1 != q1 && p2 != q2) {
            if (*(p1) != *(p2))
                return false
            ++(p1); ++(p2)
        }
        (p2 == q2) && (p1 == q1)
    }
}
