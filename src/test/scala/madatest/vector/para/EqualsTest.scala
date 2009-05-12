

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.para


import mada.vector._
import mada.vector.compatibles._

import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class EqualsTest {
    def testTrivial: Unit = {
        assertTrue(example1.parallel(1000) == mada.vector.from(example1))
        assertTrue(example1.parallel(6) equalsTo example1)
        assertTrue(example1.parallel == mada.vector.from(example1))
        assertFalse(example1.parallel == mada.vector.from(example2))
    }
}
