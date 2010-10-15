

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence.reactive
import junit.framework.Assert._
import mada.sequence.iterative


class OnNthTest extends org.scalatest.junit.JUnit3Suite {

    def testHead {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a.onHead(_ => out.add(99)).foreach(e => out.add(e))
        assertEquals(iterative.Of(99,1,2,3,4,5), iterative.from(out))
    }

    def testTrivial {
        val a = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        a.onNth(2)(_ => out.add(99)).foreach(e => out.add(e))
        assertEquals(iterative.Of(1,2,99,3,4,5), iterative.from(out))
    }

}
