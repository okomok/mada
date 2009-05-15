

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.traversabletest


import mada.traversable
import junit.framework.Assert._


class JavaTest {

    def testJioReader: Unit = {
        val arr = mada.Vector('a','b','c').toArray
        val tr = traversable.from(new java.io.CharArrayReader(arr))
        assertEquals(traversable.of('a','b','c'), tr)
        assertEquals(traversable.of('a','b','c'), tr) // traverse again.
    }

}
