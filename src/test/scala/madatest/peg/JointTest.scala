

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg
import junit.framework.Assert._


class JointTest {
    def testTrivial: Unit = {
        val p = Peg.joint("abc", "de", "fghi", "jk")
        assertTrue(p matches "abc")
        assertTrue(p matches "abcde")
        assertTrue(p matches "abcdefghi")
        assertTrue(p matches "abcdefghijk")

        assertTrue(p >> "d" matches "abcd")
        assertTrue(p >> "fg" matches "abcdefg")
        assertTrue(p >> "j" matches "abcdefghij")
        assertTrue(p >> "d" matches "abcd")

        assertFalse(p matches "ab")
        assertFalse(p matches "abcfghi")
        assertFalse(p matches "abjk")
        assertFalse(p matches "")
    }

    def testBound: Unit = {
        val p = Peg.joint[Char](Iterator.empty)
        assertTrue(p matches "")
        assertFalse(p matches "a")
    }
}
