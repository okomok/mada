

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Peg


class ByNeedActionsTest {
    def testTrivial: Unit = {
        val B = new Peg.ByNeedActions[Char]
        var c = 0
        val p = "abc" >> B.need( ( Peg.from("def"){B{_ => c+=1}} >> Peg.from("ghi"){B{_ => c*=3}} ) | ("ihjk") )

        assertTrue(p matches  "abcihjk")
        assertEquals(0, c)
        assertTrue(p matches  "abcdefghi")
        assertEquals((0+1)*3, c)
        assertFalse(p matches "abcdefgh")
        assertEquals((0+1)*3, c)

        assertTrue(p matches  "abcdefghi")
        assertEquals((3+1)*3, c)
    }
}