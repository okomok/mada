
package madatest.rng


import detail.Example._
import mada.rng.From._
import mada.rng.ToRng._
import mada.rng.ToStream._
import junit.framework.Assert._


class StreamTest {
    def testTrivial {
        detail.TestForwardReadOnly(Array(0,1,2,3,4,5,6,7), from(Stream.range(0, 8)).eval)
    }

    def testFrom {
        detail.TestForwardReadOnly(example1, from(Stream.fromIterator(example1.elements)).eval)
    }

    def testTo {
        assertEquals(Stream.fromIterator(example1.elements).toString, from(Stream.fromIterator(example1.elements)).toStream.eval.toString)
    }

    def testFusion {
        val stm = Stream.fromIterator(example1.elements)
        assertSame(stm, from(stm).toStream.eval)
    }

    def testEmpty {
        detail.TestEmpty(from(Stream.empty).eval)
    }
}
