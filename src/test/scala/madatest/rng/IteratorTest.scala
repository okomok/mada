
package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.ToIterator._
import junit.framework.Assert._


class IteratorTest {
    def testFrom {
        detail.TestSinglePassReadOnly(example1, from(example1.elements).eval)
    }

    def testTo {
        assertEquals(example1.elements.toList.toString, from(example1).toIterator.eval.toList.toString)
    }

    def testFusion {
        val it = example1.elements
        assertSame(it, from(it).toIterator.eval)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1.elements).eval)
    }
}
