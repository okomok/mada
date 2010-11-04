

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class ZipTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
    //    new NotStartable[Int].zip(new NotStartable[Int])
        val t = iterative.Of(1,2,3)
        val u = iterative.Of("2","3","4")
        assertEquals(t.zip(u), iterative.Of((1,"2"),(2,"3"),(3,"4")))
        assertEquals(t.zip(u), iterative.Of((1,"2"),(2,"3"),(3,"4")))
        AssertNotEquals(t.zip(u), iterative.Of((1,"2"),(2,"3"),(3,"5")))
    }

    def testEmpty1: Unit = {
        val t = iterative.empty.of[Int]
        val u = iterative.Of(2,3,4)
        assertTrue(t.zip(u).isEmpty)
    }

    def testEmpty2: Unit = {
        val t = iterative.empty.of[Int]
        val u = iterative.empty.of[Int]
        assertTrue(t.zip(u).isEmpty)
    }

    def testShorten: Unit = {
        val t = iterative.Of(1,2)
        val u = iterative.Of(2,3,4)
        assertEquals(t.zip(u), iterative.Of((1,2),(2,3)))
        assertEquals(t.zip(u), iterative.Of((1,2),(2,3)))
    }
/*
    def test3: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of("2","3","4")
        val v = iterative.Of('3', '4', '5')
        assertEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'4'),(3,"4",'5')))
        assertEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'4'),(3,"4",'5')))
        AssertNotEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'2'),(3,"4",'5')))
    }

    def test3Shorter: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of("2","3","4")
        val v = iterative.Of('3', '4')
        assertEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'4')))
        assertEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'4')))
        AssertNotEquals(iterative.zip(t,u,v), iterative.Of((1,"2",'3'),(2,"3",'2')))
    }
*/
    def testMap3: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of("2","3","4")
        val v = iterative.Of('3', '4', '5')
        assertEquals((t zip u zip v).map3(), iterative.Of((1,"2",'3'),(2,"3",'4'),(3,"4",'5')))
        assertEquals((t zip u zip v).map3(), iterative.Of((1,"2",'3'),(2,"3",'4'),(3,"4",'5')))
        AssertNotEquals((t zip u zip v).map3(), iterative.Of((1,"2",'3'),(2,"3",'2'),(3,"4",'5')))
    }

    def testMap4: Unit = {
        val t = iterative.Of(1,2,3)
        val u = iterative.Of("2","3","4")
        val v = iterative.Of('3', '4', '5')
        val w = iterative.Of(0.4,0.5,0.6)
        assertEquals((t zip u zip v zip w).map4(), iterative.Of((1,"2",'3',0.4),(2,"3",'4',0.5),(3,"4",'5',0.6)))
    }
}
