

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.listtest


import mada.sequence.list
import junit.framework.Assert._


class FolderTest {
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
}
