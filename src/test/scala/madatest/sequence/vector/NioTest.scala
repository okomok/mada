

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.{Vector, vector}
import mada.sequence.vector.fromJList
import madatest.sequencetest.vectortest.detail.NewArrayList
import junit.framework.Assert._


class NioTest {
    def testCharBuffer: Unit = {
        val v = vector.from(java.nio.CharBuffer.allocate(10))
        assertTrue(v.isInstanceOf[vector.FromJCharBuffer])
        assertEquals(10, v.size)
        v.nth(3) = 'a'
        assertEquals('a', v.nth(3))
    }

    def testIntBuffer: Unit = {
        val v = vector.from(java.nio.IntBuffer.allocate(10))
        assertEquals(10, v.size)
        v.nth(3) = 999
        assertEquals(999, v.nth(3))
    }
}
