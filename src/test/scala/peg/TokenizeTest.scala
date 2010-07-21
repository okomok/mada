

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._
import mada.peg.Compatibles._

import mada.sequence.vector.Region


class TokenizeTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.sequence.vector.from("XabcdXXabcdXX")
        val it = pe.tokenize(v.nth).toSeq.iterator
        var c = 0
        while (it.hasNext) {
            val Region(_, i, j) = it.next
            if (c == 0) {
                assertEquals(1, i)
                assertEquals(5, j)
            }
            if (c == 1) {
                assertEquals(7, i)
                assertEquals(11, j)
            }
            c += 1
        }
        assertEquals(2, c)
    }

    def testBound: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.sequence.vector.from("qqabqqab")
        val it = pe.tokenize(v).toSeq.iterator
        assertFalse(it.hasNext)
    }

    def testBound2: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.sequence.vector.from("abcd")
        val it = pe.tokenize(v.nth).toSeq.iterator
        val Region(_, i, j) = it.next
        assertEquals(0, i)
        assertEquals(4, j)
        assertFalse(it.hasNext)
    }

    // seems appropriate.
    def testEmpty: Unit = {
        val pe = mada.peg.from("")
        val v = mada.sequence.vector.from("")
        val it = pe.tokenize(v).toSeq.iterator
        assertFalse(it.hasNext)
    }

    def testTokens: Unit = {
        val pe = single('a') >> (dot.+ until ~"XX")
        val v = mada.sequence.vector.from("XabcdXXaBCDXX")
        val it = pe.tokenize(v.nth).toSeq.iterator
        var c = 0
        while (it.hasNext) {
            val w = it.next
            if (c == 0) {
                assertEquals(mada.sequence.vector.from("abcd"), w)
            }
            if (c == 1) {
                assertEquals(mada.sequence.vector.from("aBCD"), w)
            }
            c += 1
        }
        assertEquals(2, c)
    }
}
