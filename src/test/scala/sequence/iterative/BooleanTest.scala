

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class BooleanTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val tr = iterative.Of(2,4,6)
        val t = tr.begin
        t.toBoolean //
        assertTrue(t.toBoolean)
        assertTrue(t)
        assertTrue(!t.isEnd)
        assertFalse(!t)
        assertFalse(t.isEnd)

        // SLS is weird.
        assertTrue(!t.isEnd && !t.isEnd)
        assertTrue(t && t)
        assertTrue(t || neverCall)
        assertTrue(t || t)

        if (t) {
        } else if (t && t) {
        } else if (t || t) {
        } else if (t) {
        } else {
            fail("doh")
        }

        while (t) {
            t.++
        }

        assertTrue(!t)
        assertFalse(t)
        assertFalse(t && neverCall)

        while (!t) {
            return
        }
    }

    def neverCall: Boolean = {
        fail("not short-circuit")
        throw new Error
    }
}
