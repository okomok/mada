

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Filter._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class FilterTest {
    def testTrivial {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        val actual = from(example1).filter(_ >= 10).eval
        assertFalse(actual models Traversal.RandomAccess)
        detail.TestBidirectionalReadWrite(expected, actual)
    }

    def testSinglePass {
        val expected = Array(18,14,17,19,13,23,12,15,11)
        detail.TestSinglePassReadOnly(expected, from(example1).asRngBy(Traversal.SinglePass).filter(_ >= 10).eval)
    }

    def testFusion {
        val expected = Array(18,14,17,13,12,15,11)
        detail.TestBidirectionalReadWrite(expected, from(example1).filter(_ >= 10).filter(_ <= 18).eval)
//        println(from(example1).filter(_ >= 10).filter(_ <= 18).eval.toString)
    }

    def testEmpty {
        detail.TestEmpty(from(example1).filter(_ >= 99).eval)
    }
}
