

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.vector._
import mada.sequence.{Vector, vector}

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._


class FolderTest {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from(  Array(1,2,3,4,5,6,7,8))
        val w = mada.sequence.vector.from(Array(5,6,8,11,15,20,26,33,41))
        assertEquals(w, v.folder(5)(_ + _))
    }

    def testEmpty: Unit = {
        assertEquals(vector.single(0), mada.sequence.vector.from(empty1).folder(0)(_ + _))
    }
}
