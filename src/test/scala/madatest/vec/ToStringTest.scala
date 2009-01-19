

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector.Compatibles._
import junit.framework.Assert._


class ToStringTest {
    def testTrivial: Unit = {
        assertEquals(0, "[a, b, c, d, e]".compareTo(madaVector("abcde").toString))
        assertEquals(0, "[a]".compareTo(madaVector("a").toString))
        assertEquals(0, "[]".compareTo(madaVector("").toString))
    }
}
