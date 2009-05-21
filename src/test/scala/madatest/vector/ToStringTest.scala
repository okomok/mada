

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest



import junit.framework.Assert._


class ToStringTest {
    def testTrivial: Unit = {
        val k: mada.Vector[Char] = mada.vector.from("abcde")
       // println(k.toString)

        assertEquals(0, "[a, b, c, d, e]".compareTo(mada.vector.from("abcde").toString))
        assertEquals(0, "[a]".compareTo(mada.vector.from("a").toString))
        assertEquals(0, "[]".compareTo(mada.vector.from("").toString))
    }
}
