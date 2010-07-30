

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class AddSeqTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _4 :: _3 :: _1 :: _2 :: _5 :: _0 :: Nil
        val xs: xs = _4 :: _3 :: _1 :: _2 :: _5 :: _0 :: Nil

        type m   = set.sorted[nat.ord]#add[_8]#addSeq[xs]
        val m: m = set.sorted(nat.ord).add(_8).addSeq(xs)

        type l   = _0 :: _1 :: _2 :: _3 :: _4 :: _5 :: _8 :: Nil
        val l: l = _0 :: _1 :: _2 :: _3 :: _4 :: _5 :: _8 :: Nil
        meta.assertSame[l, m#toList]
        assertEquals(l, m.toList)
   }

    def testTrivialNil {
        type m   = set.sorted[nat.ord]#add[_8]#addSeq[Nil]
        val m: m = set.sorted(nat.ord).add(_8).addSeq(Nil)

        type l   = _8 :: Nil
        val l: l = _8 :: Nil
        meta.assertSame[l, m#toList]
        assertEquals(l, m.toList)
   }

}