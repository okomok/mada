

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.Distance._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class DistanceTest {
    def testTrivial {
        assertEquals(from(example1).distance.eval, 15L)
    }

    def testSinglePass {
        assertEquals(from(example1).asRngBy(Traversal.SinglePass).distance.eval, 15L)
    }
}
