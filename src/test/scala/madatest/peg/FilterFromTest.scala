

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._



class FilterFromTest {
    def testTrivial: Unit = {
        val pe = mada.Peg.from("abcd")
        val v = mada.vector.from("XabcdXXabcdXX")
        val it = pe.filterFrom(v)
        assertEquals(mada.vector.from("abcdabcd"), mada.vector.fromIterable(it))
    }
}
