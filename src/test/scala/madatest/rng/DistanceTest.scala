
package madatest.rng


import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.Distance._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class DistanceTest {
    def testTrivial {
        assertEquals(from(example1).rng_distance.eval, 15L)
    }

    def testSinglePass {
        assertEquals(from(example1).rng_asRngBy(SinglePassTraversal).rng_distance.eval, 15L)
    }
}
