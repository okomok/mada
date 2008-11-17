
package madatest.rng


import mada.NDebug
import mada.rng._
import junit.framework.Assert._


class TraversalTest {
    def testTrivial {
        NDebug.value = false
        assertTrue(RandomAccessTraversal <:< SinglePassTraversal)
        assertEquals(RandomAccessTraversal, RandomAccessTraversal lower BidirectionalTraversal)
        assertEquals(BidirectionalTraversal, RandomAccessTraversal upper BidirectionalTraversal)
    }
}
