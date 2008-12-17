

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.Expr
import mada.rng._
import mada.rng.From._
import mada.rng.stl.Find._
import mada.rng.Find._
import mada.rng.Exists._
import mada.rng.Forall._
import mada.rng.Pointer._
import junit.framework.Assert._


class FindTest {
    def testFind {
        val r = from(2, 100)
        assertEquals(r.find((_: Int) == 30).eval.get, 30)
        assertEquals(*(r.stl_findIf((_: Int) == 30).eval), 30)
    }

    def testExists {
        val r = from(2, 100)
        assertTrue(r.exists((_: Int) == 30).eval)
        assertFalse(r.exists((_: Int) == 200).eval)
    }

    def testForall {
        val r = from(2, 100)
        assertTrue(r.forall((_: Int) < 300).eval)
        assertFalse(r.forall((_: Int) == 50).eval)
    }
}
