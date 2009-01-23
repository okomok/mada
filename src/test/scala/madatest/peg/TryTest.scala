

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
        val p = `try` { "abc" >> error >> "d" } `catch` { case _: Error => thrown = true; "abcd" }
        assertFalse(thrown)
        assertTrue(p matches "abcd")
        assertTrue(thrown)
    }

    def testVerify: Unit = {
        var thrown = false
        val q = "abc" >> verify("R") >> "d"
        val p = `try` { q } `catch` { case VerificationException(_,(v,_,_)) => thrown = true; v(3) = 'R'; q }
        assertFalse(thrown)
        assertTrue(p matches madaVector("abcLd").clone)
        assertTrue(thrown)
    }
}
