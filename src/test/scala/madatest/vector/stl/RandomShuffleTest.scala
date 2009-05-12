

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.vectortest.stltest


import mada.vector._
import mada.{Vector, vector}
import junit.framework.Assert._
import madatest.vectortest.detail.Example._
import madatest.vectortest.detail._


class RandomShuffleTest {
    def testTrivial: Unit = {
        val v = fromArray(example1)
        mada.vector.stl.randomShuffle(v, 0, v.size)
//        println(v.toString)
        mada.vector.stl.sort(v, 0, v.size)
        TestVectorReadOnly(example1Sorted, v)
    }
}
