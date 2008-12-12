

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.rng


import mada.NDebug
import mada.rng.Window._
import mada.rng.ArrayCompatible._
import mada.rng.From._
import junit.framework.Assert._


class WindowTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(from(Array(1,2,3,4)).eval, from(Array(0,1,2,3,4,5,6)).window(1L,5L).eval)
        assertEquals(from(Array(3,4)).eval, from(Array(0,1,2,3,4,5,6)).window(1L,5L).window(2L,4L).eval)
    }
}
