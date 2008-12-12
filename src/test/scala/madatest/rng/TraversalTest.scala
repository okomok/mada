

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng._
import junit.framework.Assert._


class TraversalTest {
    def testTrivial {
        NDebug.value = false
        assertTrue(Traversal.RandomAccess <:< Traversal.SinglePass)
        assertEquals(Traversal.RandomAccess, Traversal.RandomAccess lower Traversal.Bidirectional)
        assertEquals(Traversal.Bidirectional, Traversal.RandomAccess upper Traversal.Bidirectional)
    }
}
