

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.{Peg, peg}
import mada.{Vector, vector}
import junit.framework.Assert._


class RepeatTest {
    def testRepeat: Unit = {
        assertTrue(peg.from("abc").repeat(2, 4) matches "abcabc")
        assertTrue(peg.from("abc").repeat(2, 4) matches "abcabcabc")
        assertTrue(peg.from("abc").repeat(2, 4) matches "abcabcabcabc")
        assertTrue(peg.from("abc").repeat(2, 4) >> "abc" matches "abcabcabcabcabc")

        assertFalse(peg.from("abc").repeat(2, 4) matches "")
        assertFalse(peg.from("abc").repeat(2, 4) matches "abc")
        assertFalse(peg.from("abc").repeat(2, 4) matches "abcabcabcabcabc")
    }

    def testExactly: Unit = {
        assertTrue(peg.from("abc").repeat(4) matches "abcabcabcabc")
        assertTrue(peg.from("abc").repeat(4) >> "abc" matches "abcabcabcabcabc")

        assertFalse(peg.from("abc").repeat(4) matches "")
        assertFalse(peg.from("abc").repeat(4) matches "abcabcabc")
        assertFalse(peg.from("abc").repeat(4) matches "abcabcabcabcabc")

    }

    def testAtLeast: Unit = {
        assertTrue(peg.from("abc").repeat(2, ()) matches "abcabc")
        assertTrue(peg.from("abc").repeat(2, ()) matches "abcabcabc")
        assertTrue(peg.from("abc").repeat(2, ()) matches "abcabcabcabc")

        assertFalse(peg.from("abc").repeat(2, ()) matches "")
        assertFalse(peg.from("abc").repeat(2, ()) matches "abc")
    }
}


class RepeatUntilTest {
    val any3 = peg.dot[Char] >> peg.dot[Char] >> peg.dot[Char]

    def testRepeat: Unit = {
        assertTrue(any3.repeat(2, 4).until("DE") matches "abcabcDE")
        assertTrue(any3.repeat(2, 4).until("DE") matches "aDEabcDE")
        assertTrue(any3.repeat(2, 4).until("DE") matches "abcabcabcDE")
        assertTrue(any3.repeat(2, 4).until("DE") matches "abDEbcabcDE")
        assertTrue(any3.repeat(2, 4).until("DE") matches "abcabcabcabcDE")
        assertTrue(any3.repeat(2, 4).until("DE") >> "abc" matches "abcabcabcabcDEabc")

        assertFalse(any3.repeat(2, 4).until("DE") matches "DE")
        assertFalse(any3.repeat(2, 4).until("DE") matches "abcDE")
        assertFalse(any3.repeat(2, 4).until("DE") matches "abcabcabcabcabcDE")
    }

    def testAtLeast: Unit = {
        assertTrue(any3.repeat(2, ()).until("DE") matches "abcabcDE")
        assertTrue(any3.repeat(2, ()).until("DE") matches "abcabcabcDE")
        assertTrue(any3.repeat(2, ()).until("DE") matches "abcabcabcabcDE")

        assertFalse(any3.repeat(2, ()).until("DE") matches "DE")
        assertFalse(any3.repeat(2, ()).until("DE") matches "abcDE")
    }
}
