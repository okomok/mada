

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package reactivetest; package generatortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class OfTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Generator.Of(1,2,3,4,5)
        val s = new java.util.ArrayList[Int]
        t.sequence.foreach(s.add(_))
        t.generate
        t.generate
        assertEquals(vector.Of(1,2), vector.from(s))
        assertFalse(t.isEmpty)
        s.clear

        t.generate
        t.generate
        t.generate
        assertEquals(vector.Of(3,4,5), vector.from(s))
        assertTrue(t.isEmpty)

        // no effects.
        t.generate
        t.generate
    }
}
