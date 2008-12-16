

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Cycle._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class CycleTest {
    def testTrivial {
        val expected = Array(4,23,0,12,4,23,0,12,4,23,0,12,4,23,0,12)
        val actual = from(Array(4,23,0,12)).cycle(4).eval
        detail.TestRandomAccessReadOnly(expected, actual)
    }
}
