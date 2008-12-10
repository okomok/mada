
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Buffered._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class BufferedTest {
    def testTrivial {
        detail.TestForwardReadOnly(example1, from(example1).asRngBy(SinglePassTraversal).buffered.eval)
    }

    def testTrivial2 {
        detail.TestForwardReadOnly(example1, from(example1.elements).buffered.eval)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1).asRngBy(SinglePassTraversal).buffered.eval)
        detail.TestEmpty(from(empty1.elements).buffered.eval)
    }
}
