

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Pegs._
import junit.framework.Assert._
import mada.Pegs.Compatibles._



class LookTest {
    def testAhead: Unit = {
        assertTrue("abc" >>?~ "def" >> "def" matches "abcdef")
        assertTrue("abc" >>?~  __*  >> "def" matches "abcdef")
        assertTrue("abc" >>?! "xxx" >> "def" matches "abcdef")
    }

    def testBehind: Unit = {
        assertTrue("abc" >>?<~ "abc" >> "def" matches "abcdef")
        assertTrue("abc" >>?<! "xxx" >> "def" matches "abcdef")
    }

    def testBack: Unit = {
        assertTrue("abc" >>?<<~ "cba" >> "def" matches "abcdef")
        assertTrue("abc" >>?<<~  __*  >> "def" matches "abcdef")
        assertTrue("abc" >>?<<! "abc" >> "def" matches "abcdef")
    }
}
