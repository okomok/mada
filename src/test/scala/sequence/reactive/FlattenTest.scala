

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class FlattenTest extends org.scalatest.junit.JUnit3Suite {

    def testFlatMap: Unit = {
        val tr = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]
        tr.flatMap{e => reactive.fromIterative(vector.range(0, e))}.foreach(out.add(_))
        assertEquals(iterative.Of(0,0,1,0,1,2,0,1,2,3,0,1,2,3,4), iterative.from(out))
    }

    def testUnsplit: Unit = {
        val r1 = reactive.Of(1,2)
        val r2 = reactive.Of(3,4)
        val r3 = reactive.Of(5)
        val r4 = reactive.Of(6,7,8,9)
        val rs = reactive.Of(r1,r2,r3,r4)
        val sep = reactive.Of(77,88)
        val out = new java.util.ArrayList[Int]
        rs.unsplit(sep).foreach(out.add(_))
        assertEquals(iterative.Of(1,2,77,88,3,4,77,88,5,77,88,6,7,8,9), iterative.from(out))
    }

}
