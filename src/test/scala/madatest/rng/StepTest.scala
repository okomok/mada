
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Step._
import mada.rng.From._
import mada.rng.Distance._
import junit.framework.Assert._
import detail.Example._


class StepTest {
    def testTrivial {
        // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
        // 0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4
        val expected = Array(0,17,13,23,15)
        val actual = from(example1).step(3).eval
        detail.TestForwardReadOnly(expected, actual)
    }

    def testSinglePass {
        val expected = Array(0,17,13,23,15)
        val actual = from(example1).asRngBy(Traversal.SinglePass).step(3).eval
        detail.TestSinglePassReadOnly(expected, actual)
    }

    def testBounds {
        val expected = Array(0, 6, 4)
        val actual = from(example1).step(7).eval
        detail.TestForwardReadOnly(expected, actual)
    }

    def testOne {
        val actual = from(example1).step(1).eval
        detail.TestForwardReadOnly(example1, actual)
    }

    def testBigStep {
        val r = from(example1).step(99).eval
        assertEquals(r.begin.read, 0)
        assertEquals(r.toExpr.distance.eval, 1L)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1).step(10).eval)
    }
}
