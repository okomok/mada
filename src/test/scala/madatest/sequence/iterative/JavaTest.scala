

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package iterativetest


import mada.sequence.iterative
import junit.framework.Assert._


class JavaTest {

    def testJioReader: Unit = {
//        val arr = mada.sequence.Vector('a','b','c').toArray // hmm, mada.newArray goes wrong?
        val arr = Array('a','b','c')
        val tr = iterative.from(new java.io.CharArrayReader(arr))
        assertEquals(iterative.Of('a','b','c'), tr)
        assertEquals(iterative.Of('a','b','c'), tr) // traverse again.
    }

}
