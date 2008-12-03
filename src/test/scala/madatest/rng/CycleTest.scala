
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Cycle._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class CycleTest {
    def testTrivial {
        mada.NDebug.value = false
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = from(Array(4,23,0,12)).rng_cycle(4).eval
        detail.TestRandomAccessReadOnly(expected, actual)
    }

    def testEmpty {
//        detail.TestEmpty(from(empty1).rng_cycle(10).eval)
    }
}
