

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import junit.framework.Assert._
import mada.sequence.{Vector, vector}

import mada.peg.compatibles._
import mada.peg._


class TryTest {
    def testTrivial: Unit = {
        var thrown = false
        val p = `try` { "abc" >> undefined >> "d" } `catch` { case _: java.lang.AssertionError => thrown = true; "abcd" }
        assertFalse(thrown)
        assertTrue(p matches "abcd")
        assertTrue(thrown)
    }

    def testVerify: Unit = {
        var thrown = false
        val q = "abc" >> verify("R") >> "d"
        val p = `try` { q } `catch` { case VerificationException(_, v) => thrown = true; v(3) = 'R'; q }
        assertFalse(thrown)
        assertTrue(p matches mada.sequence.vector.from("abcLd").copy)
        assertTrue(thrown)
    }

    def testFinally: Unit = {
        var thrown = false
        var final_ = false
        val p = `try` { "abc" >> undefined >> "d" } `catch` { case _: java.lang.AssertionError => thrown = true; "abcd" } `finally` { case _ => final_ = true }
        assertFalse(thrown)
        assertFalse(final_)
        assertTrue(p matches "abcd")
        assertTrue(thrown)
        assertTrue(final_)
    }
}
