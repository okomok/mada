
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Untokenize._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class UntokenizeTest {
    def sep = from(single(100))

    def testTrivial {
        val r1 = from(Array(0,18,14,17)).eval
        val r2 = from(Array(19, 8,13)).eval
        val r3 = from(Array(6, 4,23, 0,12,15)).eval
        val r4 = from(Array(11, 4)).eval
        val actual = from(Array[Rng[Int]](r1, r2, r3, r4)).untokenize(sep, RandomAccessTraversal).eval
        detail.TestBidirectionalReadOnly(Array(100,0,18,14,17,100,19,8,13,100,6,4,23,0,12,15,100,11,4), actual)
    }

    def testNontrivial {
        val re = from(empty1).eval
        val r1 = from(Array(0,18,14,17)).eval
        val r2 = from(Array(19, 8,13)).eval
        val r3 = from(Array(6, 4,23, 0,12,15)).eval
        val r4 = from(Array(11, 4)).eval
        val actual = from(Array[Rng[Int]](re, r1, re, re, re, r2, r3, re, r4, re)).untokenize(sep, RandomAccessTraversal).eval
        detail.TestBidirectionalReadOnly(Array(100,100,0,18,14,17,100,100,100,100,19,8,13,100,6,4,23,0,12,15,100,100,11,4,100), actual)
    }

    def testSinglePass {
        val r1 = from(Array(0,18,14,17)).eval
        val r2 = from(Array(19, 8,13)).eval
        val r3 = from(Array(6, 4,23, 0,12,15)).eval
        val r4 = from(Array(11, 4)).eval
        val actual = from(Array[Rng[Int]](r1, r2, r3, r4)).untokenize(sep).eval
        detail.TestSinglePassReadOnly(Array(100,0,18,14,17,100,19,8,13,100,6,4,23,0,12,15,100,11,4), actual)
    }

    def testEmpty {
        detail.TestEmpty(from(Array[Rng[Int]]()).untokenize(sep).eval)
    }
}
