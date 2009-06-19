

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.paralleltest


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class CopyTest {
    def testTrivial: Unit = {
        val actual = mada.sequence.vector.from(example1).parallel.copy
        detail.TestVectorReadWrite(example1, actual)
    }

    def testCopyTo: Unit = {
        val v = Of(1,2,3,4,5,6)
        val w = Of(9,8,7,6,5,4,3,2,1)
        assertEquals(Of(1,2,3,4,5,6), v.parallel.copyTo(w))
    }
}
