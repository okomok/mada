

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class ZipTest {
    def testTrivial: Unit = {
        new NotStartable[Int].zip(new NotStartable[Int])
        val t = sequence.of(1,2,3)
        val u = sequence.of("2","3","4")
        assertEquals(t.zip(u), sequence.of((1,"2"),(2,"3"),(3,"4")))
        assertEquals(t.zip(u), sequence.of((1,"2"),(2,"3"),(3,"4")))
        AssertNotEquals(t.zip(u), sequence.of((1,"2"),(2,"3"),(3,"5")))
    }

    def testEmpty1: Unit = {
        val t = sequence.emptyOf[Int]
        val u = sequence.of(2,3,4)
        assertTrue(t.zip(u).isEmpty)
    }

    def testEmpty2: Unit = {
        val t = sequence.emptyOf[Int]
        val u = sequence.emptyOf[Int]
        assertTrue(t.zip(u).isEmpty)
    }

    def testShorten: Unit = {
        val t = sequence.of(1,2)
        val u = sequence.of(2,3,4)
        assertEquals(t.zip(u), sequence.of((1,2),(2,3)))
        assertEquals(t.zip(u), sequence.of((1,2),(2,3)))
    }
}