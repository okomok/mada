

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.peg.Compatibles._
import mada.{Peg, peg}


class ByNeedActionsTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val B = new peg.ByNeedActions[Char]
        var c = 0
        val p = "abc" >> B.need( ( peg.from("def"){B{_ => c+=1}} >> peg.from("ghi"){B{_ => c*=3}} ) | ("ihjk") )

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
