
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class ForTest {
    def testTrivial {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        val actual = for (e <- from(example1)!; if (e >= 10)) yield (e)
        detail.TestSinglePassReadOnly(expected, actual)
    }

    def testEach {
        def doNothing[A](a: A) { }
        for (i <- from(1, 10)!; j <- from(1, i)!; if (i + j > 7)) doNothing((i, j))
    }

    def testInterval {
        val r = for (i <- from(1, 10)!; j <- from(1, i)!; if (i + j > 7)) yield (i, j)
//        println(r.toString)
        ()
    }
}
