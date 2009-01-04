

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg.parser


import mada.peg.Parser._
import mada.peg._
import junit.framework.Assert._


class EpsTest {
    def testTrivial: Unit = {
        assertTrue(eps[Int].matches(mada.Vector.empty[Int]))
        assertFalse(eps[Int].matches(mada.Vector.fromValues(1,2,3)))
    }

    def testCompile(v: mada.Vector[Char]): Unit = {
        (stringParser("abcd") ~ eps).parse(v, 0, 10)
    }
}
