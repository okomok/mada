

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.func


import mada.Functions
import junit.framework.Assert._


class FutureTest {
    class Wrap(val i: Int)

    def testTrivial: Unit = {
        val a = Functions.future(new Wrap(11))
        assertEquals(11, a().i)
        assertSame(a(), a())
    }
}
