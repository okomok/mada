

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vec


import mada.Vector
import mada.Vector.fromArray
import junit.framework.Assert._


class PermutationTest {
    def testTrivial: Unit = {
        val a = Vector.range(0, 6).clone
        val b = Vector.fromValues[Int](2,3,1,0,5,4)
        detail.TestVectorReadWrite(Array(2,3,1,0,5,4), a.permutation(b.nth))
    }

    def testRegion: Unit = {
        val a = Vector.range(0, 6).region(1, 5).clone // 1,2,3,4
        val b = Vector.fromValues[Int](2,3,1,0,5,4) //b.nth: 0->2, 1->3, 2->1, 3->0, 4->5, 5->4
        detail.TestVectorReadWrite(Array(3, 4, 2, 1), a.permutation(b.nth))
    }
/*
    def testEmpty: Unit = {
        val a = Vector.range(0, 6).clone
        val b = Vector.fromValues[Int]()
        detail.TestEmpty(a.permutation(b.nth))
    }
*/
}
