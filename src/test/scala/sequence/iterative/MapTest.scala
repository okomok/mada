

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class MapTest {
    def testTrivial: Unit = {
    //    new NotStartable[Int].map(_ + 1)
        val t = iterative.Of(1,2,3)
        val u = iterative.Of(2,3,4)
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        val t = iterative.empty.of[Int]
        val u = iterative.empty.of[Int]
        val k = t.map(_ + 1)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testFusion: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of(7,8,9)
        val k = t.map(_ + 1).map(_ + 2).map(_ + 3)

        import iterative.Map
        k match {
            case Map(Map(s, f), g) => fail
            case _ => ()
        }

        assertEquals(u, k)
        assertEquals(u, k)
    }
}
