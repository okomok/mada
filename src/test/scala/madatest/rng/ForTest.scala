
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class ForTest {
    def testTrivial {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        val actual = for (e <- from(example1).eval; if (e >= 10)) yield (e)
        detail.TestSinglePassReadOnly(expected, actual)
    }

    def testInterval {
        val r = for (i <- from(1, 10).eval; j <- from(1, i).eval; if (i + j > 7)) yield (i, j)
//        println(r.toString)
        ()
    }
}
