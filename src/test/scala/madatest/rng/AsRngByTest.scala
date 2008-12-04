
package madatest.rng


import mada.rng._
import mada.rng.From._
import mada.rng.AsRngBy._
import detail.Example._
import junit.framework.Assert._


class AsRngByTest {
    def testTrivial {
        val r = from(example1).rng_asRngBy(ForwardTraversal).eval
        assertEquals(ForwardTraversal, r.traversal)
    }

    def testNotrivial {
        val r = from(example1).rng_asRngBy(ForwardTraversal).rng_asRngBy(SinglePassTraversal).eval
        assertEquals(SinglePassTraversal, r.traversal)
    }

    def testFusion {
        val r = from(example1).rng_asRngBy(ForwardTraversal).rng_asRngBy(ForwardTraversal).rng_asRngBy(ForwardTraversal).eval
        assertEquals(ForwardTraversal, r.traversal)
    }

    def testFusion2 {
        val r = from(example1).eval
        val rr = r.toExpr.rng_asRngBy(RandomAccessTraversal).rng_asRngBy(RandomAccessTraversal).rng_asRngBy(RandomAccessTraversal).eval
        assertEquals(r, rr)
    }
}
