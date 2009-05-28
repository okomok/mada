

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._
import mada.peg.compatibles._

import mada.vector.Region


class TokenizeTest {
    def testTrivial: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.vector.from("XabcdXXabcdXX")
        val it = pe.tokenize(v.nth).toSSequence.iterator
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
        val v = mada.vector.from("qqabqqab")
        val it = pe.tokenize(v).toSSequence.iterator
        assertFalse(it.hasNext)
    }

    def testBound2: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.vector.from("abcd")
        val it = pe.tokenize(v.nth).toSSequence.iterator
        val Region(_, i, j) = it.next
        assertEquals(0, i)
        assertEquals(4, j)
        assertFalse(it.hasNext)
    }

    // seems appropriate.
    def testEmpty: Unit = {
        val pe = mada.peg.from("")
        val v = mada.vector.from("")
        val it = pe.tokenize(v).toSSequence.iterator
        assertFalse(it.hasNext)
    }

    def testTokens: Unit = {
        val pe = single('a') >> (dot.+ until ~"XX")
        val v = mada.vector.from("XabcdXXaBCDXX")
        val it = pe.tokenize(v.nth).toSSequence.iterator
        var c = 0
        while (it.hasNext) {
            val w = it.next
            if (c == 0) {
                assertEquals(mada.vector.from("abcd"), w)
            }
            if (c == 1) {
                assertEquals(mada.vector.from("aBCD"), w)
            }
            c += 1
        }
        assertEquals(2, c)
    }
}
