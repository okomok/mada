

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Pegs._
import junit.framework.Assert._


class EpsTest {
    def testTrivial: Unit = {
        assertTrue(eps[Int].matches(mada.Vectors.empty[Int]))
        assertFalse(eps[Int].matches(mada.Vectors.fromValues(1,2,3)))
    }

    def testCompile(v: mada.Vector[Char]): Unit = {
        (fromString("abcd") >> eps).parse(v, 0, 10)
    }
}
