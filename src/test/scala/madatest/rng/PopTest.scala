
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.Pop._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class PopTest {
    def testTrivial {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).rng_pop.eval
        detail.TestRandomAccessReadWrite(example1, actual)
    }

    def testForward {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).rng_asRngBy(ForwardTraversal).rng_pop.eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testSinglePass {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).rng_asRngBy(SinglePassTraversal).rng_pop.eval
        detail.TestSinglePassReadOnly(example1, actual)
    }

    def testEmpty {
        detail.TestEmpty(from(single(99)).rng_pop.eval)
        detail.TestEmpty(from(single(99)).rng_asRngBy(ForwardTraversal).rng_pop.eval)
        detail.TestEmpty(from(single(99)).rng_asRngBy(SinglePassTraversal).rng_pop.eval)
    }
}
