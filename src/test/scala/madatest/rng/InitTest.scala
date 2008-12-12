
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.Init._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class InitTest {
    def testTrivial {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).init.eval
        detail.TestRandomAccessReadWrite(example1, actual)
    }

    def testForward {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).asRngBy(Traversal.Forward).init.eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testSinglePass {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4,99)).asRngBy(Traversal.SinglePass).init.eval
        detail.TestSinglePassReadOnly(example1, actual)
    }

    def testEmpty {
        detail.TestEmpty(from(single(99)).init.eval)
        detail.TestEmpty(from(single(99)).asRngBy(Traversal.Forward).init.eval)
        detail.TestEmpty(from(single(99)).asRngBy(Traversal.SinglePass).init.eval)
    }
}
