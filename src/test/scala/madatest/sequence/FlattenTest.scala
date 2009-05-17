

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class FlattenTest {
    def testTrivial: Unit = {
        new NotStartable[sequence.Type[Int]]().flatten
        val t1 = sequence.of(0,1,2)
        val t2 = sequence.of(3,4)
        val t3 = sequence.emptyOf[Int]
        val t4 = sequence.of(5,6)
        val t5 = sequence.of(7,8,9,10)
        val t = sequence.of(t1, t2, t3, t4, t5).flatten
        val a = sequence.of(0,1,2,3,4,5,6,7,8,9,10)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty: Unit = {
        val t1 = sequence.emptyOf[Int]
        val t2 = sequence.of(3,4)
        val t3 = sequence.emptyOf[Int]
        val t4 = sequence.emptyOf[Int]
        val t = sequence.of(t1, t2, t3, t4).flatten
        val a = sequence.of(3,4)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty2: Unit = {
        val t1 = sequence.emptyOf[Int]
        val t2 = sequence.emptyOf[Int]
        val t3 = sequence.emptyOf[Int]
        val t = sequence.of(t1, t2, t3).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testEmpty3: Unit = {
        val t1 = sequence.emptyOf[Int]
        val t = sequence.of(t1).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testFlatMap: Unit = {
        val t = sequence.of(1,2,3,4,5).flatMap(e => sequence.single(e))
        val a = sequence.of(1,2,3,4,5)
        assertEquals(t, a)
        assertEquals(t, a)
    }
}
