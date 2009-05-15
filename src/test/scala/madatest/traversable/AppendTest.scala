

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class AppendTest {

    def testTrivial: Unit = {
        new NotStartable[Int] ++ new NotStartable[Int]
        val t = traversable.of(4,5,1,3)
        val u = traversable.of(9,7,10)
        val v = traversable.of(4,5,1,3,9,7,10)
        val k = t ++ u
        assertEquals(v, k)
        assertEquals(v, k)
    }

    def testEmpty: Unit = {
        val k = traversable.emptyOf[Int] ++ traversable.emptyOf[Int]
        assertEquals(traversable.emptyOf[Int], k)
        assertEquals(traversable.emptyOf[Int], k)
    }

    def testEmpty2: Unit = {
        val t = traversable.of(4,5,1,3)
        val t_  = traversable.of(4,5,1,3)
        val k = traversable.emptyOf[Int] ++ t
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testEmpty3: Unit = {
        val t = traversable.of(4,5,1,3)
        val t_  = traversable.of(4,5,1,3)
        val k = t ++ traversable.emptyOf[Int]
        assertEquals(t_, k)
        assertEquals(t_, k)
    }

    def testNonTrivial: Unit = {
        val t1 = traversable.of(4,5)
        val t2 = traversable.of(1)
        val t3 = traversable.of(3, 9)
        val t4 = traversable.emptyOf[Int]
        val t5 = traversable.of(7,10,11)
        val v = traversable.of(4,5,1,3,9,7,10,11)
        val k = t1 ++ t2 ++ t3 ++ t4 ++ t5
        assertEquals(v, k)
        assertEquals(v, k)
    }

}
