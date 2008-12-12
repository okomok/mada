

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.rng._
import mada.rng.From._
import mada.rng.AsRngBy._
import detail.Example._
import junit.framework.Assert._


class AsRngByTest {
    def testTrivial {
        val r = from(example1).asRngBy(Traversal.Forward).eval
        assertEquals(Traversal.Forward, r.traversal)
    }

    def testNotrivial {
        val r = from(example1).asRngBy(Traversal.Forward).asRngBy(Traversal.SinglePass).eval
        assertEquals(Traversal.SinglePass, r.traversal)
    }

    def testFusion {
        val r = from(example1).asRngBy(Traversal.Forward).asRngBy(Traversal.Forward).asRngBy(Traversal.Forward).eval
        assertEquals(Traversal.Forward, r.traversal)
    }

    def testFusion2 {
        val r = from(example1).eval
        val rr = r.toExpr.asRngBy(Traversal.RandomAccess).asRngBy(Traversal.RandomAccess).asRngBy(Traversal.RandomAccess).eval
        assertEquals(r, rr)
    }
}
