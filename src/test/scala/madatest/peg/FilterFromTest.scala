

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Pegs._
import junit.framework.Assert._
import mada.Pegs.Compatibles._



class FilterFromTest {
    def testTrivial: Unit = {
        val pe = mada.Pegs.from("abcd")
        val v = mada.Vectors.from("XabcdXXabcdXX")
        val it = pe.filterFrom(v)
        assertEquals(mada.Vectors.from("abcdabcd"), mada.Vectors.fromIterator(it))
    }
}
