

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ScanLeftTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8)
        val u = vector.Of(5,6,8,11,15,20,26,33,41)
        val s = new java.util.ArrayList[Int]
        t.scanLeft(5)(_ + _).foreach(s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testTrivial2: Unit = {
        val t = reactive.Of(1)
        val u = vector.Of(5,6)
        val s = new java.util.ArrayList[Int]
        t.scanLeft(5)(_ + _).foreach(s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].scanLeft(0)(_ + _).foreach(s.add(_))
        assertEquals(vector.Of(0), vector.from(s))
    }

/*
    def testReducerLeft: Unit = {
        val t = reactive.Of(5,1,2,3,4,5,6,7,8)
        val u = vector.Of(5,6,8,11,15,20,26,33,41, 99)
        val s = new java.util.ArrayList[Int]
        t.reducerLeft(_ + _).activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(u, vector.from(s))
    }

    def testReducerLeftEmpty: Unit = {
        val t = reactive.Of(5,1)
        val u = vector.Of(5,6, 99)
        val s = new java.util.ArrayList[Int]
        t.reducerLeft(_ + _).activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(u, vector.from(s))
    }

    def testReducerEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].reducerLeft(_ + _).activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(99), vector.from(s))
    }
*/
}

class ScanLeft1Test extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val t = reactive.Of(5,1,2, 3, 4, 5, 6, 7, 8)
        val u = vector.  Of(5,6,8,11,15,20,26,33,41)
        val s = new java.util.ArrayList[Int]
        t.scanLeft1(_ + _).foreach(s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testTrivial2: Unit = {
        val t = reactive.Of(5,1)
        val u = vector.Of(5,6)
        val s = new java.util.ArrayList[Int]
        t.scanLeft1(_ + _).foreach(s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].scanLeft1(_ + _).foreach(s.add(_))
        assertTrue(vector.from(s).isEmpty)
    }
}

