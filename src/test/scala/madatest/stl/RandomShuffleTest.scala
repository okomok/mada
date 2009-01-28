

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.stl


import mada.Vector._
import mada.Vector
import junit.framework.Assert._
import madatest.vec.detail.Example._
import madatest.vec.detail._


class RandomShuffleTest {
    def testTrivial: Unit = {
        val v = arrayVector(example1)
        mada.Stl.randomShuffle(v, 0, v.size)
//        println(v.toString)
        mada.Stl.sort(v, 0, v.size)
        TestVectorReadOnly(example1Sorted, v)
    }
}
