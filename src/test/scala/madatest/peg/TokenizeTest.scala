

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._

import mada.Vectors.Region


class TokenizeTest {
    def testTrivial: Unit = {
        val pe = madaPeg("abcd")
        val v = mada.Vectors.from("XabcdXXabcdXX")
        val it = pe.tokenize(v)
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
        val pe = madaPeg("abcd")
        val v = mada.Vectors.from("qqabqqab")
        val it = pe.tokenize(v)
        assertFalse(it.hasNext)
    }

    def testBound2: Unit = {
        val pe = madaPeg("abcd")
        val v = mada.Vectors.from("abcd")
        val it = pe.tokenize(v)
        val Region(_, i, j) = it.next
        assertEquals(0, i)
        assertEquals(4, j)
        assertFalse(it.hasNext)
    }

    // seems appropriate.
    def testEmpty: Unit = {
        val pe = madaPeg("")
        val v = mada.Vectors.from("")
        val it = pe.tokenize(v)
        assertFalse(it.hasNext)
    }

    def testTokens: Unit = {
        val pe = single('a') >> any.+?("XX")
        val v = mada.Vectors.from("XabcdXXaBCDXX")
        val it = pe.tokenize(v)
        var c = 0
        while (it.hasNext) {
            val w = it.next
            if (c == 0) {
                assertEquals(mada.Vectors.from("abcd"), w)
            }
            if (c == 1) {
                assertEquals(mada.Vectors.from("aBCD"), w)
            }
            c += 1
        }
        assertEquals(2, c)
    }
}
