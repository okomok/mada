

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence.list
import junit.framework.Assert._


class FolderTest {
// left
    def testFolderLeft: Unit = {
        val t = list.Of(1,2,3,4,5,6,7,8)
        val u = list.Of(5,6,8,11,15,20,26,33,41)
        val k = t.folderLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testFolderLeft1: Unit = {
        val t = list.Of(1)
        val u = list.Of(5,6)
        val k = t.folderLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        assertEquals(list.single(0), list.emptyOf[Int].folderLeft(0)(_ + _))
    }

    def testReducerLeft: Unit = {
        val t = list.Of(5,1,2,3,4,5,6,7,8)
        val u = list.Of(5,6,8,11,15,20,26,33,41)
        val k = t.reducerLeft(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

// right
    def testFolderRight: Unit = {
        val L = list.Of(1,2,3,4)
        val A = list.Of(15,14,12,9,5)
        assertEquals(A, L.folderRight(5)(_ + _()))
    }

    def testReducerRight: Unit = {
        val L = list.Of(1,2,3,4)
        val A = list.Of(10,9,7,4)
        assertEquals(A, L.reducerRight(_ + _()))
    }

    def testFolderRightInfinite: Unit = {
        val L = list.Of(false,false,true,false).cycle
        val A = list.repeat(true)
        val L_ = L.folderRight(false)(_ || _()).take(50)
        assertEquals(A.take(50), L_)
        ()
    }

    def testReducerRightInfinite: Unit = {
        val L = list.Of(false,false,true,false).cycle
        val A = list.repeat(true)
        assertEquals(A.take(50), L.reducerRight(_ || _()).take(50))
    }

}
