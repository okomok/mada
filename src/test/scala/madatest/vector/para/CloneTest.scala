

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.para


import mada.vector._

import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class CloneTest {
    def testTrivial: Unit = {
        val actual = mada.vector.from(example1).parallel.copy
        detail.TestVectorReadWrite(example1, actual)
    }
}
