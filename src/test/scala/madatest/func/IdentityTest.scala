

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.func


import mada.Functions
import junit.framework.Assert._


class IdentityTest {
    def testTrivial: Unit = {
        assertEquals(11, Functions.identity[Int].apply(11))
        assertEquals("11", Functions.identity[String].apply("11"))
        assertEquals(11, Functions.identity.apply(11))
        assertEquals("11", Functions.identity.apply("11"))
        assertEquals(11, Functions.identity(11))
        assertEquals("11", Functions.identity("11"))
    }
}
