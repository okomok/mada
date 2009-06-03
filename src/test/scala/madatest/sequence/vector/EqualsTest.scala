

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest


import mada.sequence.{Vector, vector}
import junit.framework.Assert._


class EqualsTest {
    def testNull: Unit = {
        assertFalse(vector.range(1, 3) == null)
    }
}
