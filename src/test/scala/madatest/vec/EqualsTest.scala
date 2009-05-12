

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest


import mada.Vector
import junit.framework.Assert._


class EqualsTest {
    def testNull: Unit = {
        assertFalse(Vector.range(1, 3) == null)
    }
}
