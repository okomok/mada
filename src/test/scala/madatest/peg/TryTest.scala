

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import junit.framework.Assert._
import mada.Vector
import mada.Vector.Compatibles._
import mada.Peg.Compatibles._
import mada.Peg._


class TryTest {
    def testTrivial: Unit = {
        var thrown = false
        val p = try_ { "abc" >> error >> "d" } catch_ { case _: Error => thrown = true; "abcd" }
        assertFalse(thrown)
        assertTrue(p matches "abcd")
        assertTrue(thrown)
    }

    def testExpected: Unit = {
        var thrown = false
        val q = "abc" >> "R".expected >> "d"
        val p = try_ { q } catch_ { case ExpectedException(_,(v,_,_)) => thrown = true; v(3) = 'R'; q }
        assertFalse(thrown)
        assertTrue(p matches madaVector("abcLd").clone)
        assertTrue(thrown)
    }
}
