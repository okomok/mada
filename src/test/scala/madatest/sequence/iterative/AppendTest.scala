

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class AppendTest {

    def testTrivial: Unit = {
    //    new NotStartable[Int] ++ new NotStartable[Int]
        val t = iterative.Of(4,5,1,3)
        val u = iterative.Of(9,7,10)
        val v = iterative.Of(4,5,1,3,9,7,10)
        val k = t ++ u
        assertEquals(v, k)
        assertEquals(v, k)
    }

    def testEmpty: Unit = {
        val k = iterative.empty.of[Int] ++ iterative.empty.of[Int]
        assertEquals(iterative.empty.of[Int], k)
        assertEquals(iterative.empty.of[Int], k)
    }

    def testEmpty2: Unit = {
        val t = iterative.Of(4,5,1,3)
        val t_  = iterative.Of(4,5,1,3)
        val k = iterative.empty.of[Int] ++ t
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testEmpty3: Unit = {
        val t = iterative.Of(4,5,1,3)
        val t_  = iterative.Of(4,5,1,3)
        val k = t ++ iterative.empty.of[Int]
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testNonTrivial: Unit = {
        val t1 = iterative.Of(4,5)
        val t2 = iterative.Of(1)
        val t3 = iterative.Of(3, 9)
        val t4 = iterative.empty.of[Int]
        val t5 = iterative.Of(7,10,11)
        val v = iterative.Of(4,5,1,3,9,7,10,11)
        val k = t1 ++ t2 ++ t3 ++ t4 ++ t5
        assertEquals(v, k)
        assertEquals(v, k)
    }

}
