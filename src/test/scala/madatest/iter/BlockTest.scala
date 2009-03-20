

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
        withMakeValuesTo(2)
        withMakeValuesTo(3)
        withMakeValuesTo(9)
        withMakeValuesTo(11)
        withMakeValuesTo(19)
        withMakeValuesTo(20)
        withMakeValuesTo(21)
        withMakeValuesTo(25)
        withMakeValuesTo(30)
        withMakeValuesTo(60)
        withMakeValuesTo(67)
        withMakeValuesTo(80)
        withMakeValuesTo(82)
        withMakeValuesTo(300)
        withMakeValuesTo(310)
    }

    def throwSome(y: Iterables.Yield[Int]): Unit = {
        for (i <- 1 to 27) {
            y(i)
        }
        throw new Error()
    }

    def testThrow(testOff: Int): Unit = {
        val it = Iterables.block(throwSome)
        var ret = false
        try {
            ret = Iterables.equal(1 to 27, it)
        } catch {
            case e: Error =>
        }
        assertTrue(ret)
        ret = false
        try {
            ret = Iterables.equal(1 to 27, it) // run again.
        } catch {
            case e: Error =>
        }
        assertTrue(ret)
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
