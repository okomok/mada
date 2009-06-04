

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.vectortest.parallelstest


import mada.sequence.vector._

import junit.framework.Assert._
import madatest.sequencetest.vectortest.detail.Example._
import madatest.sequencetest.vectortest.detail._


class FoldTest {
    def testTrivial: Unit = {
        val v = mada.sequence.vector.from(example1)
        assertEquals(v.foldLeft(3)(_ + _), v.parallel.fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallel(6).fold(3)(_ + _))
        assertEquals(v.foldLeft(3)(_ + _), v.parallel(1000).fold(3)(_ + _))
    }
}
