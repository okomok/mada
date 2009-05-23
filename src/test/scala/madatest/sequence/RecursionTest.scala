

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence._
import junit.framework.Assert._


class RecursionTest {

  // which way do you like?

    def testLazy: Unit = {
        lazy val tr: Type[Int] = Of(1,2,3) ++ byName(tr)
        assertEquals(Of(1,2,3,1,2,3,1,2,3,1), tr.take(10))
    }

    def testVar: Unit = {
        val tr = new Var[Int]
        assertTrue(tr.isNull)
        tr := Of(1,2,3) ++ tr
        assertFalse(tr.isNull)
        assertEquals(Of(1,2,3,1,2,3,1,2,3,1), tr.take(10))
        tr := null
        assertTrue(tr.isNull)
    }

    /*
    def testVar2: Unit = {
        var tr: Type[Int] = null
        tr = Of(1,2,3) ++ tr
        assertEquals(Of(1,2,3,1,2,3,1,2,3,1), tr.take(10))
    }
    */
}
