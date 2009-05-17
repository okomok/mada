

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence
import junit.framework.Assert._


class JavaTest {

    def testJioReader: Unit = {
        val arr = mada.Vector('a','b','c').toArray
        val tr = sequence.from(new java.io.CharArrayReader(arr))
        assertEquals(sequence.of('a','b','c'), tr)
        assertEquals(sequence.of('a','b','c'), tr) // traverse again.
    }

}
