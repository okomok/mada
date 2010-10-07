

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package sequencetest; package reactivetest; package generatortest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class SingleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Generator.by(iterative.single(1))
        val s = new java.util.ArrayList[Int]
        t.sequence.foreach(s.add(_))
        assertFalse(t.isEmpty)
        t.generate
        assertEquals(vector.Of(1), vector.from(s))
        assertTrue(t.isEmpty)

        // no effects.
        t.generate
        t.generate
    }
}
