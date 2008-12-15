

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng.stl


import mada.rng.From._
import mada.rng.stl.Fill._
import junit.framework.Assert._
import mada.rng.jcl.ArrayList


class FillTest {
    def testTrivial = {
        val a = ArrayList(1,2,3,4,5,6)
        from(a).stl_fill(9).eval
        assertEquals(from(ArrayList(9,9,9,9,9,9)).eval, from(a).eval)
    }
}
