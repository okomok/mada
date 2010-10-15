

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._
import mada.sequence.iterative


class OnHeadTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a.onHead(out.add(99)).foreach(e => out.add(e))
        assertEquals(iterative.Of(99,1,2,3,4,5), iterative.from(out))
    }

}
