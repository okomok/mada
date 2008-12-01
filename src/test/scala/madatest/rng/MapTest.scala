
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Map._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class MapTest {
    def testTrivial {
        val expected = Array(1,19,15,18,20, 9,14, 7, 5,24, 1,13,16,12, 5)
        val actual = from(example1).rng_map(_ + 1).eval
        detail.TestRandomAccessReadOnly(expected, actual)
    }

    def testFusion {
        detail.TestRandomAccessReadOnly(example1, from(example1).rng_map(_ + 1).rng_map(_ - 1).eval)
//        println(from(example1).rng_map(_ + 1).rng_map(_ - 1).eval.toString)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1).rng_map(_ + 1).eval)
    }
}
