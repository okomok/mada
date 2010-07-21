

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class PutListTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = Tuple2[_4, _5] :: Tuple2[_3, _4] :: Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_5, _6] :: Tuple2[_0, _1] :: Nil
        val xs: xs = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_5, _6) :: Tuple2(_0, _1) :: Nil

        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[xs]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(xs)

        type l   = Tuple2[_0, _1] :: Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_3, _4] :: Tuple2[_4, _5] :: Tuple2[_5, _6] :: Tuple2[_8, _9] :: Nil
        val l: l = Tuple2(_0, _1) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_3, _4) :: Tuple2(_4, _5) :: Tuple2(_5, _6) :: Tuple2(_8, _9) :: Nil
        meta.assertSame[l, m#toList]
        assertEquals(l, m.toList)
   }

    def testTrivialNil {
        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[Nil]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(Nil)

        type l   = Tuple2[_8, _9] :: Nil
        val l: l = Tuple2(_8, _9) :: Nil
        meta.assertSame[l, m#toList]
        assertEquals(l, m.toList)
   }

}
