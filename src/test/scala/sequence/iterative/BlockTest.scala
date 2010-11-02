

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._
import scala.util.continuations.suspendable


class BlockTest extends org.scalatest.junit.JUnit3Suite {
    def makeEmpty(y: Int => Unit@ suspendable): Unit @suspendable = ()

    def testEmpty: Unit = {
        val tr = iterative.block(makeEmpty)
        assertTrue(tr.isEmpty)
        assertTrue(tr.isEmpty) // run again.
    }

    def makeValuesTo(n: Int)(y: Int => Unit @suspendable): Unit @suspendable = {
        var i = 1
        while (i <= n) {
            y(i)
            i += 1
        }
    }

    def withMakeValuesTo(n: Int): Unit = {
        val tr = iterative.block(makeValuesTo(n))
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
        def example = iterative.block[Any] { `yield` =>
            `yield`("first")
            var i = 1
            while (i < 4) {
                `yield`(i)
                i += 1
            }
            `yield`("last")
        }
        for (a <- example) {
            //println(a)
        }
    }

    def testExceptionForwarding: Unit = {
        def throwSome(y: Int => Unit @suspendable): Unit @suspendable = {
            var i = 1
            while (i <= 27) {
                y(i)
                i += 1
            }
            throw new Error("exception forwarding")
        }

        val tr = iterative.block(throwSome)

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
        def throwImmediately(y: Int => Unit @suspendable): Unit @suspendable = {
            throw new Error("exception forwarding")
        }
        val tr = iterative.block(throwImmediately)

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
}
