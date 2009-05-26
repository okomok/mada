

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.{Sequence, sequence}
import junit.framework.Assert._


class MemoizeTest {
    def testTrivial: Unit = {
        var memo = false
        val v = sequence.from(Array(0,1,2,3,4,5)).map{ x => if (memo) fail("doh"); x + 10 }
        val w = v.memoize
        assertEquals(sequence.from(Array(10,11,12,13,14,15)), w)
        memo = true
        assertEquals(sequence.from(Array(10,11,12,13,14,15)), w)
    }
}
