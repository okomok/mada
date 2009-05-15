

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class FolderTest {
    def testFolderLeft: Unit = {
        new NotStartable[Int].folderLeft(5)(_ + _)
        val t = traversable.of(1,2,3,4,5,6,7,8)
        val u = traversable.of(5,6,8,11,15,20,26,33,41)
        val k = t.folderLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testFolderLeft1: Unit = {
        val t = traversable.of(1)
        val u = traversable.of(5,6)
        val k = t.folderLeft(5)(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testEmpty: Unit = {
        assertEquals(traversable.single(0), traversable.emptyOf[Int].folderLeft(0)(_ + _))
    }

    def testReducerLeft: Unit = {
        val t = traversable.of(5,1,2,3,4,5,6,7,8)
        val u = traversable.of(5,6,8,11,15,20,26,33,41)
        val k = t.reducerLeft(_ + _)
        assertEquals(u, k)
        assertEquals(u, k)
    }
}
