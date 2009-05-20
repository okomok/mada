

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.pegtest


import mada.peg._
import junit.framework.Assert._
import mada.peg.compatibles._



class FilterFromTest {
    def testTrivial: Unit = {
        val pe = mada.peg.from("abcd")
        val v = mada.vector.from("XabcdXXabcdXX")
        val it = pe.filterFrom(v)
        assertEquals(mada.vector.from("abcdabcd"), it)
        assertEquals(mada.vector.from("abcdabcd"), it)
    }
}
