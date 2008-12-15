

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.stl


import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.stl.Distance._
import mada.rng.From._
import junit.framework.Assert._
import madatest.rng.detail.Example._


class DistanceTest {
    def testTrivial {
        assertEquals(from(example1).stl_distance.eval, 15L)
    }

    def testSinglePass {
        assertEquals(from(example1).asRngBy(Traversal.SinglePass).stl_distance.eval, 15L)
    }
}
