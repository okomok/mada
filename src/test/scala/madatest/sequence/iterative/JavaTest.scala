

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class JavaTest {

    def testJioReader: Unit = {
        val arr = mada.Vector('a','b','c').toArray
        val tr = iterative.from(new java.io.CharArrayReader(arr))
        assertEquals(iterative.Of('a','b','c'), tr)
        assertEquals(iterative.Of('a','b','c'), tr) // traverse again.
    }

}
