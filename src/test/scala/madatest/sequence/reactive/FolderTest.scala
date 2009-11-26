

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package reactivetest


import mada.sequence._
import junit.framework.Assert._


class FolderTest {
    def testFolderLeft: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8)
        val u = vector.Of(5,6,8,11,15,20,26,33,41, 99)
        val s = new java.util.ArrayList[Int]
        t.folderLeft(5)(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testFolderLeft1: Unit = {
        val t = reactive.Of(1)
        val u = vector.Of(5,6, 99)
        val s = new java.util.ArrayList[Int]
        t.folderLeft(5)(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testFolderEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].folderLeft(0)(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(vector.Of(99), vector.from(s))
    }


    def testReducerLeft: Unit = {
        val t = reactive.Of(5,1,2,3,4,5,6,7,8)
        val u = vector.Of(5,6,8,11,15,20,26,33,41, 99)
        val s = new java.util.ArrayList[Int]
        t.reducerLeft(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testReducerLeftEmpty: Unit = {
        val t = reactive.Of(5,1)
        val u = vector.Of(5,6, 99)
        val s = new java.util.ArrayList[Int]
        t.reducerLeft(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(u, vector.from(s))
    }

    def testReducerEmpty: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].reducerLeft(_ + _).subscribe(_ => s.add(99), s.add(_))
        assertEquals(vector.Of(99), vector.from(s))
    }

}
