

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class TokenizeTest {
    def testTrivial: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("XabcdXXabcdXX")
        val it = pe.tokenize(v)
        var c = 0
        while (it.hasNext) {
            val (_, i, j) = it.next
            if (c == 0) {
                assertEquals(1L, i)
                assertEquals(5L, j)
            }
            if (c == 1) {
                assertEquals(7L, i)
                assertEquals(11L, j)
            }
            c += 1
        }
        assertEquals(2, c)
    }

    def testBound: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("qqabqqab")
        val it = pe.tokenize(v)
        assertFalse(it.hasNext)
    }

    def testBound2: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("abcd")
        val it = pe.tokenize(v)
        val (_, i, j) = it.next
        assertEquals(0L, i)
        assertEquals(4L, j)
        assertFalse(it.hasNext)
    }

    // seems appropriate.
    def testEmpty: Unit = {
        val pe = madaPeg("")
        val v = madaVector("")
        val it = pe.tokenize(v)
        assertFalse(it.hasNext)
    }

    def testTokens: Unit = {
        val pe = single('a') >> any.+?("XX")
        val v = madaVector("XabcdXXaBCDXX")
        val it = pe.tokens(v)
        var c = 0
        while (it.hasNext) {
            val w = it.next
            if (c == 0) {
                assertEquals(madaVector("abcd"), w)
            }
            if (c == 1) {
                assertEquals(madaVector("aBCD"), w)
            }
            c += 1
        }
        assertEquals(2, c)
    }
}
