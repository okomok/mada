

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Iterators
import junit.framework.Assert._


class IteratorsTest {
    def testEquals: Unit = {
        assertFalse(Iterators.equal(Iterator.empty, Iterator.fromValues(1,2,3)))
        assertFalse(Iterators.equal(Iterator.fromValues(1,2), Iterator.fromValues(1,2,3)))
        assertTrue(Iterators.equal(Iterator.fromValues(1,2,3), Iterator.fromValues(1,2,3)))
        assertTrue(Iterators.equal(Iterator.empty, Iterator.empty))
    }

    def testLength: Unit = {
        assertEquals(3, Iterators.length(Iterator.fromValues(1,2,3)))
        assertEquals(0, Iterators.length(Iterator.empty))
    }

    def testUnfoldRight: Unit = {
        val it = Iterators.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        assertTrue(Iterators.equal(it, Iterator.fromValues(10,9,8,7,6,5,4,3,2,1)))
    }

    def testIterate: Unit = {
        val it = Iterators.iterate(10){ b => b-1 }.take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(10,9,8,7,6,5,4,3,2,1)))
    }

    def testRepeat: Unit = {
        val it = Iterators.repeat(3).take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(3,3,3,3,3,3,3,3,3,3)))
    }

    def testCycle: Unit = {
        val it = Iterators.cycle(Iterator.fromValues(1,2,3)).take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,2,3,1,2,3,1,2,3,1)))
    }

    def testInfix: Unit = {
        import Iterators.Infix._

        assertTrue(Iterator.fromValues(1,2,3) equal Iterator.fromValues(1,2,3))
        assertEquals(3, Iterator.fromValues(1,2,3).length)

        val it = Iterator.fromValues(1,2,3).cycle.take(10)
        assertTrue(Iterators.equal(it, Iterator.fromValues(1,2,3,1,2,3,1,2,3,1)))
    }
}
