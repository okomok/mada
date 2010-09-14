

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class SingleTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.single(1)
        val s = new java.util.ArrayList[Int]
        t.foreach(s.add(_))
        assertEquals(vector.Of(1), vector.from(s))
    }
}
