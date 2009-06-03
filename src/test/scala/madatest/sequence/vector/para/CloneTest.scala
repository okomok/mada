

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.para


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class CloneTest {
    def testTrivial: Unit = {
        val actual = mada.sequence.vector.from(example1).parallel.copy
        detail.TestVectorReadWrite(example1, actual)
    }
}
