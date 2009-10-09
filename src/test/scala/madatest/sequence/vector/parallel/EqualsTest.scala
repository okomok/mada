

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package sequencetest; package vectortest; package paralleltest


import mada.sequence.vector._


import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class EqualsTest {
    def testTrivial: Unit = {
        assertTrue(from(example1).parallel(1000) == mada.sequence.vector.from(example1))
        assertTrue(from(example1).parallel(6) == mada.sequence.vector.from(example1))
        assertTrue(from(example1).parallel == mada.sequence.vector.from(example1))
        assertFalse(from(example1).parallel == mada.sequence.vector.from(example2))
    }
}
