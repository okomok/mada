

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.para


import mada.Vector._

import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class CloneTest {
    def testTrivial: Unit = {
        val actual = mada.Vector.from(example1).parallel.clone
        detail.TestVectorReadWrite(example1, actual)
    }
}
