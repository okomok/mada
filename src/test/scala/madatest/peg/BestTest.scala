

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector.Compatibles._
import mada.Vector
import mada.Peg.Compatibles._
import mada.Peg


class BestTest {
    def testLongest(i: Int): Unit = {
        val p = Peg.longest("a", "abc", "KKKKKKKKKK", "abcdefg", "abcd")
        assertTrue("123" >> p >> "LL"  matches "123abcdefgLL")
    }

    def testShortest: Unit = {
        val p = Peg.shortest("abc", "abcdefg", "K", "ab", "abcd")
        assertTrue("123" >> p >> "cdefgLL"  matches "123abcdefgLL")
    }

    def testEmpty: Unit = {
        val p2 = Peg.longest(Vector.empty[Peg[Char]].toRandomAccessSeq)
        assertFalse("123" >> p2 >> "cdefgLL"  matches "123abcdefgLL")

        val p1 = Peg.shortest("K", "B")
        assertFalse("123" >> p1 >> "cdefgLL"  matches "123abcdefgLL")
    }
}
