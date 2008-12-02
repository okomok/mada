
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.TakeWhile._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class TakeWhileTest {
    def testTrivial {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4, 99, 7,20, 1)).rng_takeWhile(_ != 99).eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testBounds {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)).rng_takeWhile(_ != 99).eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1).rng_takeWhile(_ != 99).eval)
        detail.TestEmpty(from(example1).rng_takeWhile(_ == 100).rng_takeWhile(_ == 100).eval)
    }
}
