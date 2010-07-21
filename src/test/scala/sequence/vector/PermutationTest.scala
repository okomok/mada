

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence.{Vector, vector}
import mada.sequence.vector.from
import junit.framework.Assert._


class PermutationTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val a = vector.range(0, 6).copy
        val b = vector.Of[Int](2,3,1,0,5,4)
        detail.TeztVectorReadWrite(Array(2,3,1,0,5,4), a.permutation(b.nth))
    }

    def testRegion: Unit = {
        val a = vector.range(0, 6).region(1, 5).copy // 1,2,3,4
        val b = vector.Of[Int](2,3,1,0,5,4) //b.nth: 0->2, 1->3, 2->1, 3->0, 4->5, 5->4
        detail.TeztVectorReadWrite(Array(3, 4, 2, 1), a.permutation(b.nth))
    }
/*
    def testEmpty: Unit = {
        val a = vector.range(0, 6).copy
        val b = vector.Of[Int]()
        detail.TeztEmpty(a.permutation(b.nth))
    }
*/
}
