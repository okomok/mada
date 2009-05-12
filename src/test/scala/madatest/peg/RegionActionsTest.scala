

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.vector.compatibles._
import mada.{Vector, vector}
import mada.Peg.Compatibles._
import mada.Peg


class RegionActionsTest {
    def testTrivial: Unit = {
        val R = new Peg.RegionActions[Char]
        var w: Vector[Char] = null
        val g = R.startAt >> Peg.SymbolMap("e" --> "z", "ef" --> ( R{v => w = v} >> "g" ), "wx" --> "wy", "wxyz" --> ( R{v => w = v} >> "123" ) )
        assertTrue("abc" >> g >> "LL"  matches "abcefgLL")
        assertEquals(vector.from("ef"), w)
        assertTrue("abc" >> g >> "LL"  matches "abcwxyz123LL")
        assertEquals(vector.from("wxyz"), w)
    }
}
