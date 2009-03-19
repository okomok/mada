

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import junit.framework.Assert._


class BlockTest {
    def makeValues(y: Int => Unit): Unit = {
        y(1)
        y(2)
        y(3)
        y(4)
        y(5)
    }

    def makeOne(y: Int => Unit): Int = {
        y(1)
        999
    }

    def makeEmpty(y: Int => Unit): Unit = ()

    def makeValuesTo(n: Int)(y: Int => Unit): Unit = {
        for (i <- 1 to n) {
            y(i)
        }
    }

    def testTrivial: Unit = {
        val it = Iterables.block(makeValues)
//        println(Iterables.stringFrom(it))
        assertTrue(Iterables.equal(Iterables(1,2,3,4,5), it))
        assertTrue(Iterables.equal(Iterables(1,2,3,4,5), it)) // run again.
    }

    def testCapacity: Unit = {
        withCapacity(1)
        withCapacity(2)
        withCapacity(3)
        withCapacity(4)
        withCapacity(5)
        withCapacity(6)
        withCapacity(7)
        withCapacity(8)
        withCapacity(20)
    }

    def withCapacity(n: Int): Unit = {
        val it = Iterables.block(makeValues, n)
        assertTrue(Iterables.equal(Iterables(1,2,3,4,5), it))
        assertTrue(Iterables.equal(Iterables(1,2,3,4,5), it)) // run again.
    }

    def testOne: Unit = {
        val it = Iterables.block(makeOne)
        assertTrue(Iterables.equal(Iterables(1), it))
        assertTrue(Iterables.equal(Iterables(1), it)) // run again.
    }

    def testEmpty: Unit = {
        val it = Iterables.block(makeEmpty)
        assertTrue(it.isEmpty)
        assertTrue(it.isEmpty) // run again.
    }

    def testMakeValuesTo: Unit = {
        val it = Iterables.block(makeValuesTo(4))
        assertTrue(Iterables.equal(Iterables(1,2,3,4), it))
        assertTrue(Iterables.equal(Iterables(1,2,3,4), it)) // run again.
    }
}
