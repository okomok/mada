
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Take._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class TakeTest {
    def testTrivial {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4, 8, 7,20, 1)).rng_take(15).eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testNontrivial {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4, 8, 7,20, 1)).
            rng_take(200).eval.toExpr.
            rng_take(100).eval.toExpr.
            rng_take(50).eval.toExpr.
            rng_take(15).eval.toExpr.
            rng_take(50).eval
        detail.TestForwardReadWrite(example1, actual)
//        println(actual.toString)
    }

    def testFusion {
        val actual = from(Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4, 8, 7,20, 1)).rng_take(200).rng_take(100).rng_take(50).rng_take(15).rng_take(50).eval
        detail.TestForwardReadWrite(example1, actual)
//        println(actual.toString)
    }

    def testEmpty {
        detail.TestEmpty(from(empty1).rng_take(20).eval)
        detail.TestEmpty(from(example1).rng_take(0).rng_take(0).eval)
    }
}
