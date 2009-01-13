

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada._
import junit.framework.Assert._
import mada.Vector.Compatibles._


class IteratorTest {
    def testFlatten: Unit = {
        val its = Iterator.fromValues[Iterator[Int]](Iterator.empty, Iterator.fromValues(1,2,3), Iterator.empty, Iterator.fromValues(4,5,6), Iterator.fromValues(7,8), Iterator.empty)
        assertEquals(madaVector(Array(1,2,3,4,5,6,7,8)), mada.Vector.fromIterator(IteratorFlatten(its)))
    }

    def testFlattenBound: Unit = {
        val its = Iterator.fromValues[Iterator[Int]]()
        assertFalse(its.hasNext)
    }
}
