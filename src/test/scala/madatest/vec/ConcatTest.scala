

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector._
import junit.framework.Assert._


class ConcatTest {
    def testTrivial: Unit = {
        val v1 = Vector.arrayVector(Array(0,1,2))
        val v2 = Vector.arrayVector(Array(3,4))
        val v3 = Vector.arrayVector(detail.Example.empty1)
        val v4 = Vector.arrayVector(Array(5,6))
        val v5 = Vector.arrayVector(Array(7,8,9,10))
        val vv = Vector.concat(v1,v2,v3,v4,v5)
        val e = Vector.range(0, 11)
        detail.TestVectorReadWrite(e.toArray, vv) // concat is a view; flatten is not.
    }

    def testEmpty: Unit = {
        val v1 = Vector.arrayVector(detail.Example.empty1)
        val v2 = Vector.arrayVector(detail.Example.empty1)
        val v3 = Vector.arrayVector(detail.Example.empty1)
        val vv = Vector.concat(v1,v2,v3)
        detail.TestEmpty(vv)
    }

    def testEmpty2: Unit = {
        detail.TestEmpty(Vector.concat[Int]())
    }
}
