

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.rng._
import mada.Expr
import mada.rng.From._
import mada.rng.ToRng._
import junit.framework.Assert._


class IntervalTest {

    def testTrivial = {
        assertEquals(from(2, 5).eval, from(2, 5).eval)
        assertEquals(from(2L, 5L).eval, from(2L, 5L).eval)
    }

    def testTrivial2 = {
        assertEquals(from(IntInterval(2, 5)).eval, from(2, 5).eval)
        assertEquals(from(LongInterval(2L, 5L)).eval, from(2L, 5L).eval)
    }

    def testToRng = {
        assertEquals(mada.Expr(IntInterval(2, 5)).toRng.eval, from(2, 5).eval)
        assertEquals(mada.Expr(LongInterval(2L, 5L)).toRng.eval, from(2L, 5L).eval)
    }

    def testMe {
        val ex = Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18)
        detail.TestRandomAccessReadOnly(ex, from(5, 19).eval)
    }
}
