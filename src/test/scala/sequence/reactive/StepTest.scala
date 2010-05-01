

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class StepTest {
/*
    def testStep0: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(0).take(5).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,1,1,1,1, 99), vector.from(out))
    }
*/
    def testStep1: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(1).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,2,3,4,5,6, 99), vector.from(out))
    }

    def testStep2: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(2).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,3,5, 99), vector.from(out))
    }

    def testStep3: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(3).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,4, 99), vector.from(out))
    }

    def testStepFusion: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6,7,8,9,10,11).step(3).step(2).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,7, 99), vector.from(out))
    }
/*
    def testStep02Fusion: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(0).step(2).take(3).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,1,1, 99), vector.from(out))
    }

    def testStep20Fusion: Unit = {
        val out = new java.util.ArrayList[Int]
        reactive.Of(1,2,3,4,5,6).step(0).step(2).take(3).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(1,1,1, 99), vector.from(out))
    }
*/
    def testStepEmpty: Unit = {
        val out = new java.util.ArrayList[Int]
/*
        reactive.empty.of[Int].step(0).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(99), vector.from(out))
        out.clear
*/
        reactive.empty.of[Int].step(1).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(99), vector.from(out))
        out.clear

        reactive.empty.of[Int].step(2).activate(reactor.make(_ => out.add(99), out.add(_)))
        assertEquals(vector.Of(99), vector.from(out))
        out.clear

        ()
    }

}
