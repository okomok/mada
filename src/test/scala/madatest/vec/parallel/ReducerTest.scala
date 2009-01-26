

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec.parallel


import mada.Vector._
import mada.Vector.Compatibles._
import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class ReducerTest {
    def testTrivial: Unit = {
        val v = madaVector(Array(1,2,3,4,5,6,7,8,9,10,11))
        assertEquals(v.reducer(_ + _), v.parallel.reducer(_ + _))
    }

    def testBound: Unit = {
    }
}
