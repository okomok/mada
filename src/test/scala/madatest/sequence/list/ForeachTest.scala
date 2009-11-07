

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package listtest


import mada.sequence.list
import junit.framework.Assert._


class ForeachTest {
    def testTrivial: Unit = {
        val tr = list.Of(1,2,3)
        val k1 = new java.util.ArrayList[Int]
        val k2 = new java.util.ArrayList[Int]
        tr.foreach(e => k1.add(e))
        k2.add(1); k2.add(2); k2.add(3)
        assertEquals(k1, k2)
    }

    def testEmpty: Unit = {
        val tr = list.empty.of[Int]
        val k1 = new java.util.ArrayList[Int]
        val k2 = new java.util.ArrayList[Int]
        tr.foreach(e => k1.add(e))
        assertEquals(k1, k2)
    }
}
