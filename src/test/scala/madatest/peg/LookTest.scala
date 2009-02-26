

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._


class LookTest {
    def testAhead: Unit = {
        assertTrue("abc" >> ~"def" >> "def" matches "abcdef")
        assertTrue("abc" >> ~(any.*) >> "def" matches "abcdef")
        assertTrue("abc" >> !"xxx" >> "def" matches "abcdef")
    }

    def testBehind: Unit = {
        assertTrue("abc" >> "abc".<=~ >> "def" matches "abcdef")
        assertTrue("abc" >> "xxx".<=! >> "def" matches "abcdef")
    }

    def testBack: Unit = {
        assertTrue("abc" >> "cba".<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> ("cba" >> end).<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> any.*.<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> "abc".<<! >> "defg" matches "abcdefg")

        assertTrue("abc" >> ("d".<=~ >> "cb" >> ~"a").<<~ >> "defg" matches "abcdefg")
        assertTrue("abc" >> regex("(?<=d)cb(?=a)").<<~ >> "defg" matches "abcdefg")
    }
}
