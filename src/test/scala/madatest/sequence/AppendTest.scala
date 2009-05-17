

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class AppendTest {

    def testTrivial: Unit = {
        new NotStartable[Int] ++ new NotStartable[Int]
        val t = sequence.of(4,5,1,3)
        val u = sequence.of(9,7,10)
        val v = sequence.of(4,5,1,3,9,7,10)
        val k = t ++ u
        assertEquals(v, k)
        assertEquals(v, k)
    }

    def testEmpty: Unit = {
        val k = sequence.emptyOf[Int] ++ sequence.emptyOf[Int]
        assertEquals(sequence.emptyOf[Int], k)
        assertEquals(sequence.emptyOf[Int], k)
    }

    def testEmpty2: Unit = {
        val t = sequence.of(4,5,1,3)
        val t_  = sequence.of(4,5,1,3)
        val k = sequence.emptyOf[Int] ++ t
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testEmpty3: Unit = {
        val t = sequence.of(4,5,1,3)
        val t_  = sequence.of(4,5,1,3)
        val k = t ++ sequence.emptyOf[Int]
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testNonTrivial: Unit = {
        val t1 = sequence.of(4,5)
        val t2 = sequence.of(1)
        val t3 = sequence.of(3, 9)
        val t4 = sequence.emptyOf[Int]
        val t5 = sequence.of(7,10,11)
        val v = sequence.of(4,5,1,3,9,7,10,11)
        val k = t1 ++ t2 ++ t3 ++ t4 ++ t5
        assertEquals(v, k)
        assertEquals(v, k)
    }

}
