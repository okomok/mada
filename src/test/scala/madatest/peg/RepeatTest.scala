

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg
import mada.Vector
import junit.framework.Assert._


class RepeatTest {
    def testRepeat: Unit = {
        assertTrue(Peg.from("abc").repeat(2, 4) matches "abcabc")
        assertTrue(Peg.from("abc").repeat(2, 4) matches "abcabcabc")
        assertTrue(Peg.from("abc").repeat(2, 4) matches "abcabcabcabc")
        assertTrue(Peg.from("abc").repeat(2, 4) >> "abc" matches "abcabcabcabcabc")

        assertFalse(Peg.from("abc").repeat(2, 4) matches "")
        assertFalse(Peg.from("abc").repeat(2, 4) matches "abc")
        assertFalse(Peg.from("abc").repeat(2, 4) matches "abcabcabcabcabc")
    }

    def testExactly: Unit = {
        assertTrue(Peg.from("abc").repeat(4) matches "abcabcabcabc")
        assertTrue(Peg.from("abc").repeat(4) >> "abc" matches "abcabcabcabcabc")

        assertFalse(Peg.from("abc").repeat(4) matches "")
        assertFalse(Peg.from("abc").repeat(4) matches "abcabcabc")
        assertFalse(Peg.from("abc").repeat(4) matches "abcabcabcabcabc")

    }

    def testAtLeast: Unit = {
        assertTrue(Peg.from("abc").repeat(2, ()) matches "abcabc")
        assertTrue(Peg.from("abc").repeat(2, ()) matches "abcabcabc")
        assertTrue(Peg.from("abc").repeat(2, ()) matches "abcabcabcabc")

        assertFalse(Peg.from("abc").repeat(2, ()) matches "")
        assertFalse(Peg.from("abc").repeat(2, ()) matches "abc")
    }
}
