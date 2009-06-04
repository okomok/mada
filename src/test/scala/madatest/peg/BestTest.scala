

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._
import mada.sequence.{Vector, vector}
import mada.peg.compatibles._
import mada.{Peg, peg}


class BestTest {
    def testLongest(i: Int): Unit = {
        val p = peg.longest("a", "abc", "KKKKKKKKKK", "abcdefg", "abcd")
        assertTrue("123" >> p >> "LL"  matches "123abcdefgLL")
    }

    def testShortest: Unit = {
        val p = peg.shortest("abc", "abcdefg", "K", "ab", "abcd")
        assertTrue("123" >> p >> "cdefgLL"  matches "123abcdefgLL")
    }

    def testEmpty: Unit = {
        val p2 = peg.longest(Iterable.empty.asInstanceOf[Iterable[Char]])
        assertFalse("123" >> p2 >> "cdefgLL"  matches "123abcdefgLL")

        val p1 = peg.shortest("K", "B")
        assertFalse("123" >> p1 >> "cdefgLL"  matches "123abcdefgLL")
    }
}
