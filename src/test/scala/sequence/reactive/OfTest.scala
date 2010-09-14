

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class OfTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a.foreach(e => out.add(e))
        assertEquals(iterative.Of(1,2,3,4,5), iterative.from(out))
    }
}
