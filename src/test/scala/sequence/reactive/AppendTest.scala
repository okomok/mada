

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class AppendTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val r1 = reactive.Of(1,2,3)
        val r2 = reactive.Of(4,5)
        val out = new java.util.ArrayList[Int]
        (r1 ++ r2).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(1,2,3,4,5, 99), iterative.from(out))
    }

    def testNonTrivial: Unit = {
        val r1 = reactive.Of(1,2,3)
        val r2 = reactive.Of(4,5)
        val r3 = reactive.Of(6,7,8,9)
        val out = new java.util.ArrayList[Int]
        (r1 ++ r2 ++ r3).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(1,2,3,4,5,6,7,8,9, 99), iterative.from(out))
    }

}