

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.peg


import mada.Peg._
import junit.framework.Assert._
import mada.Peg.Compatibles._
import mada.Vector.Compatibles._


class FilterFromTest {
    def testTrivial: Unit = {
        val pe = madaPeg("abcd")
        val v = madaVector("XabcdXXabcdXX")
        val it = pe.filterFrom(v)
        assertEquals(madaVector("abcdabcd"), mada.Vector.fromIterator(it))
    }
}
