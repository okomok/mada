

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class ZipTest {
    def testTrivial: Unit = {
        val t = traversable.of(1,2,3)
        val u = traversable.of("2","3","4")
        assertEquals(t.zip(u), traversable.of((1,"2"),(2,"3"),(3,"4")))
        assertEquals(t.zip(u), traversable.of((1,"2"),(2,"3"),(3,"4")))
        AssertNotEquals(t.zip(u), traversable.of((1,"2"),(2,"3"),(3,"5")))
    }

    def testEmpty1: Unit = {
        val t = traversable.emptyOf[Int]
        val u = traversable.of(2,3,4)
        assertTrue(t.zip(u).isEmpty)
    }

    def testEmpty2: Unit = {
        val t = traversable.emptyOf[Int]
        val u = traversable.emptyOf[Int]
        assertTrue(t.zip(u).isEmpty)
    }

    def testShorten: Unit = {
        val t = traversable.of(1,2)
        val u = traversable.of(2,3,4)
        assertEquals(t.zip(u), traversable.of((1,2),(2,3)))
        assertEquals(t.zip(u), traversable.of((1,2),(2,3)))
    }
}
