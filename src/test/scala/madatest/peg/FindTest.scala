

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class FindTest {
    def testTrivial: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("XabcdXXabcdXX")
        val it = pe.find(v)
        var c = 0
        while (it.hasNext) {
            val (i, j) = it.next
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
        val it = pe.find(v)
        assertFalse(it.hasNext)
    }

    def testBound2: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("abcd")
        val it = pe.find(v)
        val (i, j) = it.next
        assertEquals(0L, i)
        assertEquals(4L, j)
        assertFalse(it.hasNext)
    }
}
