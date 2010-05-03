

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class UniqueTest {

    def testTrivial: Unit = {
        val tr = reactive.Of(5,4,4,4,3,2,2,2,2,2,1)
        val out = new java.util.ArrayList[Int]
        tr.unique.activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(5,4,3,2,1, 99), iterative.from(out))
    }

    def testFusion: Unit = {
        val tr = reactive.Of(5,5,5,4,4,4,3,2,2,2,2,2,1)
        val out = new java.util.ArrayList[Int]
        tr.unique.unique.unique.activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(5,4,3,2,1, 99), iterative.from(out))
    }

    def testUnique0: Unit = {
        val tr = reactive.empty.of[Int]
        val out = new java.util.ArrayList[Int]
        tr.unique.activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(99), iterative.from(out))
    }

    def testUnique1: Unit = {
        val tr = reactive.Of(9)
        val out = new java.util.ArrayList[Int]
        tr.unique.activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(9, 99), iterative.from(out))
    }

    def testUnique2: Unit = {
        val tr = reactive.Of(9,9,9,9,9,9)
        val out = new java.util.ArrayList[Int]
        tr.unique.activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(iterative.Of(9, 99), iterative.from(out))
    }

}
