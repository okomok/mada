

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class GeneratorTest extends org.scalatest.junit.JUnit3Suite {
    def makeEmpty(y: Int => Unit): Int = 999

    def testEmpty: Unit = {
        val tr = iterative.generator(makeEmpty)
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty) // run again.
    }

    def makeValuesTo(n: Int)(y: iterative.Yield[Int]): Unit = {
        for (i <- 1 to n) {
            y(i)
        }
    }

    def withMakeValuesTo(n: Int): Unit = {
        val tr = iterative.generator(makeValuesTo(n))
        assertEquals(iterative.from(1 to n), tr)
        assertEquals(iterative.from(1 to n), tr) // run again.
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

    def throwSome(y: iterative.Yield[Int]): Unit = {
        for (i <- 1 to 27) {
            y(i)
        }
        throw new Error()
    }

    def testThrow(testOff: Int): Unit = {
        val tr = iterative.generator(throwSome)
        var ret = false
        try {
            ret = iterative.from(1 to 27) == tr
        } catch {
            case e: Error =>
        }
        assertTrue(ret)
        ret = false
        try {
            ret = iterative.from(1 to 27) == tr // run again.
        } catch {
            case e: Error =>
        }
        assertTrue(ret)
    }
}

class GeneratorLockCompile extends Benchmark {
    val b = new GeneratorTest
    val tr = iterative.generator(b.makeValuesTo(100000))
    override def run = {
        val a = tr.size
        ()
    }
}
