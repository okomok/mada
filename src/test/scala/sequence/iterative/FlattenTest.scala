

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class FlattenTest {
    def testTrivial: Unit = {
    //    new NotStartable[iterative.Type[Int]]().flatten
        val t1 = iterative.Of(0,1,2)
        val t2 = iterative.Of(3,4)
        val t3 = iterative.empty.of[Int]
        val t4 = iterative.Of(5,6)
        val t5 = iterative.Of(7,8,9,10)
        val t = iterative.Of(t1, t2, t3, t4, t5).flatten
        val a = iterative.Of(0,1,2,3,4,5,6,7,8,9,10)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty: Unit = {
        val t1 = iterative.empty.of[Int]
        val t2 = iterative.Of(3,4)
        val t3 = iterative.empty.of[Int]
        val t4 = iterative.empty.of[Int]
        val t = iterative.Of(t1, t2, t3, t4).flatten
        val a = iterative.Of(3,4)
        assertEquals(t, a)
        assertEquals(t, a)
    }

    def testEmpty2: Unit = {
        val t1 = iterative.empty.of[Int]
        val t2 = iterative.empty.of[Int]
        val t3 = iterative.empty.of[Int]
        val t = iterative.Of(t1, t2, t3).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testEmpty3: Unit = {
        val t1 = iterative.empty.of[Int]
        val t = iterative.Of(t1).flatten
        assertTrue(t.isEmpty)
        assertTrue(t.isEmpty)
    }

    def testFlatMap: Unit = {
        val t = iterative.Of(1,2,3,4,5).flatMap(e => iterative.single(e))
        val a = iterative.Of(1,2,3,4,5)
        assertEquals(t, a)
        assertEquals(t, a)
    }
}
