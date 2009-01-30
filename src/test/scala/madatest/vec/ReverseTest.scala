

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import junit.framework.Assert._
import mada.Vector.Compatibles._


class ReverseTest {
    def testTrivial: Unit = {
        assertEquals(madaVector(Array(7,6,5,4,3,2)), Vector.range(2, 8).reverse)
    }

    def testFusion: Unit = {
        assertEquals(madaVector(Array(7,6,5,4,3,2)), Vector.range(2, 8).reverse.reverse.reverse)
    }
}
