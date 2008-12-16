

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng.Foreach._
import mada.rng.Reverse._
import mada.rng.From._
import junit.framework.Assert._


class ReverseTest {
    def testTrivial {
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).reverse.eval)
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).reverse.reverse.reverse.eval)
    }

    def testFusion() {
        val l = from(Array(6,5,4,3,2,1)).xlazy
        val l1 = l.eval
        val l2 = l.reverse.reverse.eval
        assertSame(l1, l2)
    }

    def testPointer() {
        detail.NDebugReverse.value = false
        detail.TestRandomAccessReadablePointer(from(Array(6,5,4,3,2,1,0)).reverse.eval.begin, 7, Array(0,1,2,3,4,5,6))
    }
}
