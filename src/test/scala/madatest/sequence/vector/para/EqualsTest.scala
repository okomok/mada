

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.para


import mada.sequence.vector._
import mada.sequence.vector.compatibles._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class EqualsTest {
    def testTrivial: Unit = {
        assertTrue(example1.parallel(1000) == mada.sequence.vector.from(example1))
        assertTrue(example1.parallel(6) == mada.sequence.vector.from(example1))
        assertTrue(example1.parallel == mada.sequence.vector.from(example1))
        assertFalse(example1.parallel == mada.sequence.vector.from(example2))
    }
}
