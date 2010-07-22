

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class EquivToTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = Tuple2[_4, _5] :: Tuple2[_3, _4] :: Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_5, _6] :: Tuple2[_0, _1] :: Nil
        val xs: xs = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_5, _6) :: Tuple2(_0, _1) :: Nil
        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[xs]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(xs)

        type xs2    = Tuple2[_4, _5] ::Tuple2[_3, _4] ::  Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_0, _1] ::Tuple2[_5, _6] ::  Nil
        val xs2: xs2 = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_0, _1) ::Tuple2(_5, _6) ::  Nil
        type m2   = map.sorted[nat.ord]#put[_8, _9]#putList[xs2]
        val m2: m2 = map.sorted(nat.ord).put(_8, _9).putList(xs2)

        meta.assertSame[`true`, m#equivTo[m, nat.ord]]
        meta.assertSame[`true`, m#equivTo[m2, nat.ord]]
        meta.assertSame[`true`, m2#equivTo[m, nat.ord]]
        assertEquals(`true`, m.equivTo(m2, nat.ord))
    }

    def testTrivialDifferentKey {
        type xs    = Tuple2[_4, _5] :: Tuple2[_3, _4] :: Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_15, _6] :: Tuple2[_0, _1] :: Nil
        val xs: xs = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_15, _6) :: Tuple2(_0, _1) :: Nil
        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[xs]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(xs)

        type xs2    = Tuple2[_4, _5] ::Tuple2[_3, _4] ::  Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_0, _1] ::Tuple2[_5, _6] ::  Nil
        val xs2: xs2 = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_0, _1) ::Tuple2(_5, _6) ::  Nil
        type m2   = map.sorted[nat.ord]#put[_8, _9]#putList[xs2]
        val m2: m2 = map.sorted(nat.ord).put(_8, _9).putList(xs2)

        meta.assertSame[`true`, m#equivTo[m, nat.ord]]
        meta.assertSame[`false`, m#equivTo[m2, nat.ord]]
        meta.assertSame[`false`, m2#equivTo[m, nat.ord]]
        assertEquals(`false`, m.equivTo(m2, nat.ord))
    }

    def testTrivialDifferentValue {
        type xs    = Tuple2[_4, _5] :: Tuple2[_3, _4] :: Tuple2[_1, _12] :: Tuple2[_2, _3] :: Tuple2[_5, _6] :: Tuple2[_0, _1] :: Nil
        val xs: xs = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _12) :: Tuple2(_2, _3) :: Tuple2(_5, _6) :: Tuple2(_0, _1) :: Nil
        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[xs]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(xs)

        type xs2    = Tuple2[_4, _5] ::Tuple2[_3, _4] ::  Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_0, _1] ::Tuple2[_5, _6] ::  Nil
        val xs2: xs2 = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_0, _1) ::Tuple2(_5, _6) ::  Nil
        type m2   = map.sorted[nat.ord]#put[_8, _9]#putList[xs2]
        val m2: m2 = map.sorted(nat.ord).put(_8, _9).putList(xs2)

        meta.assertSame[`true`, m#equivTo[m, nat.ord]]
        meta.assertSame[`false`, m#equivTo[m2, nat.ord]]
        meta.assertSame[`false`, m2#equivTo[m, nat.ord]]
        assertEquals(`false`, m.equivTo(m2, nat.ord))
    }

    def testTrivialDifferentSize {
        type xs    = Tuple2[_4, _5] :: Tuple2[_3, _4] :: Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_5, _6] :: Nil
        val xs: xs = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_5, _6) :: Nil
        type m   = map.sorted[nat.ord]#put[_8, _9]#putList[xs]
        val m: m = map.sorted(nat.ord).put(_8, _9).putList(xs)

        type xs2    = Tuple2[_4, _5] ::Tuple2[_3, _4] ::  Tuple2[_1, _2] :: Tuple2[_2, _3] :: Tuple2[_0, _1] ::Tuple2[_5, _6] ::  Nil
        val xs2: xs2 = Tuple2(_4, _5) :: Tuple2(_3, _4) :: Tuple2(_1, _2) :: Tuple2(_2, _3) :: Tuple2(_0, _1) ::Tuple2(_5, _6) ::  Nil
        type m2   = map.sorted[nat.ord]#put[_8, _9]#putList[xs2]
        val m2: m2 = map.sorted(nat.ord).put(_8, _9).putList(xs2)

        meta.assertSame[`true`, m#equivTo[m, nat.ord]]
        meta.assertSame[`false`, m#equivTo[m2, nat.ord]]
        meta.assertSame[`false`, m2#equivTo[m, nat.ord]]
        assertEquals(`false`, m.equivTo(m2, nat.ord))
    }

    def testTrivialNil {
        type m   = map.sorted[nat.ord]
        val m: m = map.sorted(nat.ord)
        type m2   = map.sorted[nat.ord]
        val m2: m2 = map.sorted(nat.ord)

        meta.assertSame[`true`, m#equivTo[m, nat.ord]]
        meta.assertSame[`true`, m#equivTo[m2, nat.ord]]
        meta.assertSame[`true`, m2#equivTo[m, nat.ord]]
        assertEquals(`true`, m.equivTo(m2, nat.ord))
   }

}
