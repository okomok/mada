

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package functiontest


import com.github.okomok.mada

import mada.function
import junit.framework.Assert._


class IdentityTest {
    def testTrivial: Unit = {
        assertEquals(11, function.identity[Int].apply(11))
        assertEquals("11", function.identity[String].apply("11"))
        assertEquals(11, function.identity.apply(11))
        assertEquals("11", function.identity.apply("11"))
        assertEquals(11, function.identity(11))
        assertEquals("11", function.identity("11"))
    }
}
