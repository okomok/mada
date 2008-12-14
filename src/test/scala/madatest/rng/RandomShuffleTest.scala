

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Sort._
import mada.rng.RandomShuffle._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class RandomShuffleTest {
    def testTrivial {
        val r = from(example1).eval
        r./.randomShuffle./
        r./.sort./
        assertEquals(from(example1Sorted).eval, r)
    }
}
