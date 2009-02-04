

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vectors
import mada.Vectors._
import junit.framework.Assert._


class ConcatTest {
    def testTrivial: Unit = {
        val v1 = Vectors.fromArray(Array(0,1,2))
        val v2 = Vectors.fromArray(Array(3,4))
        val v3 = Vectors.fromArray(detail.Example.empty1)
        val v4 = Vectors.fromArray(Array(5,6))
        val v5 = Vectors.fromArray(Array(7,8,9,10))
        val vv = Vectors.concat(v1,v2,v3,v4,v5)
        val e = Vectors.range(0, 11)
        detail.TestVectorReadWrite(e.toArray, vv) // concat is a view; flatten is not.
    }

    def testEmpty: Unit = {
        val v1 = Vectors.fromArray(detail.Example.empty1)
        val v2 = Vectors.fromArray(detail.Example.empty1)
        val v3 = Vectors.fromArray(detail.Example.empty1)
        val vv = Vectors.concat(v1,v2,v3)
        detail.TestEmpty(vv)
    }

    def testEmpty2: Unit = {
        detail.TestEmpty(Vectors.concat[Int]())
    }
}
