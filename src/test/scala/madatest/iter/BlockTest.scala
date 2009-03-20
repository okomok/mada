

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.iter


import mada.Iterables
import junit.framework.Assert._


class BlockTest {
    def makeEmpty(y: Int => Unit): Int = 999

    def testEmpty: Unit = {
        val it = Iterables.block(makeEmpty)
        assertTrue(it.isEmpty)
        assertTrue(it.isEmpty) // run again.
    }

    def makeValuesTo(n: Int)(y: Iterables.Yield[Int]): Unit = {
        for (i <- 1 to n) {
            y(i)
        }
    }

    def withMakeValuesTo(n: Int): Unit = {
        val it = Iterables.block(makeValuesTo(n))
        assertTrue(Iterables.equal(1 to n, it))
        assertTrue(Iterables.equal(1 to n, it)) // run again.
    }

    def testTrivial: Unit = {
        withMakeValuesTo(1)
        withMakeValuesTo(3)
        withMakeValuesTo(5)
        withMakeValuesTo(7)
        withMakeValuesTo(9)
        withMakeValuesTo(11)
        withMakeValuesTo(19)
        withMakeValuesTo(20)
        withMakeValuesTo(21)
        withMakeValuesTo(25)
        withMakeValuesTo(30)
        withMakeValuesTo(300)
    }
}

class BlockLockTest extends NoBenchmark {
    val b = new BlockTest
    val it = Iterables.block(b.makeValuesTo(100000))
    override def run = {
        val a = Iterables.length(it)
        ()
    }
}
