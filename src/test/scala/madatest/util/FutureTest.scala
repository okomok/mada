

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package utiltest


import mada.util
import junit.framework.Assert._


class FutureTest {
    class Wrap(val i: Int) extends RuntimeException

    def testTrivial: Unit = {
        val a = util.future(new Wrap(11))
        assertEquals(11, a().i)
        assertSame(a(), a())
    }

    def testThrow: Unit = {
        val a = util.future{throw new Wrap(11); 12}
        var thrown = false
        try {
            a()
        } catch {
            case e: Wrap => thrown = true
        }
        assertEquals(true, thrown)
    }
}
