

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec



import junit.framework.Assert._


class ToStringTest {
    def testTrivial: Unit = {
        assertEquals(0, "[a, b, c, d, e]".compareTo(mada.Vector.from("abcde").toString))
        assertEquals(0, "[a]".compareTo(mada.Vector.from("a").toString))
        assertEquals(0, "[]".compareTo(mada.Vector.from("").toString))
    }
}
