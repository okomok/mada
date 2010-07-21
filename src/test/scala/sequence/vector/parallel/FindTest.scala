

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class FindTest extends junit.framework.TestCase {
    def testFindFirst: Unit = {
        for (k <- 0 to 50) {
            val v = vector.Of(1,3,102,1,103,3,4,104,8,10,6)
            assertEquals(102, v.parallelBy(1).find(_ > 100).get)
        }
    }

    def testTrivial: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals("kl", v.parallel.find(_ == "kl").get)
    }

    def testTrivial2: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals("kl", v.parallelBy(1).find(_ == "kl").get)
    }

    def testNull: Unit = {
        val v: Vector[String] = vector.Of("ab", "cde", null.asInstanceOf[String], "f", "ghij", "kl", "mno", "p", "qrst")
        assertEquals(null.asInstanceOf[String], v.parallelBy(1).find((_: String) eq null).get)
    }

    def testNotFound: Unit = {
        val v = vector.Of("ab", "cde", "f", "ghij", "kl", "mno", "p", "qrst")
        assertTrue(v.parallel.find(_ == "xyz").isEmpty)
    }

}
