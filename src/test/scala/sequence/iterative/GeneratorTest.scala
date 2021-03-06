

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

    def testTrivial2 {
        def example = iterative.generator[Any] { `yield` =>
            `yield`("first")
            for (i <- 1 until 4) {
                `yield`(i)
            }
            `yield`("last")
        }
        for (a <- example) {
            //println(a)
        }
    }

    def testExceptionForwarding: Unit = {
        def throwSome(y: iterative.Yield[Int]): Unit = {
            for (i <- 1 to 27) {
                y(i)
            }
            throw new Error("exception forwarding")
        }

        val tr = iterative.generator(throwSome)

        var thrown = false
        val arr = new java.util.ArrayList[Int]

        try {
            val it = tr.begin
            while (it) {
                arr.add(~it)
                it.++
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)
        assertEquals(iterative.from(1 to 27), iterative.from(arr))
    }

    def testExceptionForwardingEmpty: Unit = {
        def throwImmediately(y: iterative.Yield[Int]) {
            throw new Error("exception forwarding")
        }
        val tr = iterative.generator(throwImmediately)

        var thrown = false
        val arr = new java.util.ArrayList[Int]
        try {
            val it = tr.begin
            while (it) {
                arr.add(~it)
                it.++
            }
        } catch {
            case _: Error => thrown = true
        }
        assertTrue(thrown)
        assertTrue(arr.isEmpty)
    }

    def testFlush {
        def sample = iterative.generator[Int] { y =>
            for (i <- 0 until 20) {
                y(i)
            } // exchange.
            y(20)
            y(21)
            y(22)
            y.flush // exchange.
            Thread.sleep(80000)
        }
        val ret = new java.util.ArrayList[Int]
        val it = sample.begin
        assertEquals(0, ~it)
        for (_ <- 1 until 23) {
            it.++ // may exchange.
            ret.add(~it)
        }
        assertEquals(iterative.from(1 until 23), iterative.from(ret))
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
