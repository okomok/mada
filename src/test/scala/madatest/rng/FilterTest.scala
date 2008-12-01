
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Filter._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class FilterTest {
    def testTrivial {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        val actual = from(example1).rng_filter(_ >= 10).eval
        assertFalse(actual models RandomAccessTraversal)
        detail.TestBidirectionalReadWrite(expected, actual)
    }

    def testSinglePass {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        detail.TestSinglePassReadOnly(expected, from(example1).rng_asRngBy(SinglePassTraversal).rng_filter(_ >= 10).eval)
    }

    def testFusion {
        val expected = Array(18,14,17,13,12,15,11)
        detail.TestBidirectionalReadWrite(expected, from(example1).rng_filter(_ >= 10).rng_filter(_ <= 18).eval)
    }
}
