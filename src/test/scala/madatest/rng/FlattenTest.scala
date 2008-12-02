
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Flatten._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class FlattenTest {
    def testTrivial {
        val r1 = from(Array(0,18,14,17)).eval
        val r2 = from(Array(19, 8,13)).eval
        val r3 = from(Array(6, 4,23, 0,12,15)).eval
        val r4 = from(Array(11, 4)).eval
        val actual = from(Array[Rng[Int]](r1, r2, r3, r4)).rng_flatten.eval
        detail.TestBidirectionalReadWrite(example1, actual)
    }

    def testTraversal {
        val r1 = from(Array(0,18,14,17)).eval
        val r2 = from(Array(19, 8,13)).eval
        val r3 = from(Array(6, 4,23, 0,12,15)).eval
        val r4 = from(Array(11, 4)).eval
        val actual = from(Array[Rng[Int]](r1, r2, r3, r4)).rng_flatten(ForwardTraversal).eval
        detail.TestForwardReadWrite(example1, actual)
    }

    def testEmpty {
        val r = from(empty1).eval
        detail.TestEmpty(from(Array[Rng[Int]](r, from(empty1).eval, r)).rng_flatten.eval)
    }
}
