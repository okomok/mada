

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class UnionTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type m   = map.sorted[nat.ord]#put[_4, _5]#put[_5, _6]#put[_0, _1]
        val m: m = map.sorted(nat.ord).put(_4, _5).put(_5, _6).put(_0, _1)

        type m2 =    map.sorted[nat.ord]#put[_3, _4]#put[_1, _2]#put[_2, _3]
        val m2: m2 = map.sorted(nat.ord).put(_3, _4).put(_1, _2).put(_2, _3)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`, map.sorted[nat.ord]#put[_4, _5]#put[_3, _4]#put[_5, _6]#put[_0, _1]#put[_1, _2]#put[_2, _3]#equivTo[um, nat.ord]]
        assertEquals(map.sorted(nat.ord).put(_4, _5).put(_3, _4).put(_5, _6).put(_0, _1).put(_1, _2).put(_2, _3), um)
   }

    def testEmpty {
        type m   = map.sorted[nat.ord]
        val m: m = map.sorted(nat.ord)

        type m2 = map.sorted[nat.ord]
        val m2: m2 = map.sorted(nat.ord)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[map.sorted[nat.ord], um]
        assertEquals(map.sorted(nat.ord), um)
   }

    def testEmpty2 {
        type m   = map.sorted[nat.ord]
        val m: m = map.sorted(nat.ord)

        type m2 =    map.sorted[nat.ord]#put[_3, _4]#put[_1, _2]#put[_2, _3]
        val m2: m2 = map.sorted(nat.ord).put(_3, _4).put(_1, _2).put(_2, _3)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`,  map.sorted[nat.ord]#put[_3, _4]#put[_1, _2]#put[_2, _3]#equivTo[um, nat.ord]]
        assertEquals(map.sorted(nat.ord).put(_3, _4).put(_1, _2).put(_2, _3), um)
   }

   def testLeftBiased {
        type m   = map.sorted[nat.ord]#put[_4, _5]#put[_5, _6]#put[_0, _1]
        val m: m = map.sorted(nat.ord).put(_4, _5).put(_5, _6).put(_0, _1)

        type m2 =    map.sorted[nat.ord]#put[_3, _4]#put[_4, _9]#put[_2, _3]
        val m2: m2 = map.sorted(nat.ord).put(_3, _4).put(_4, _9).put(_2, _3)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`,  map.sorted[nat.ord]#put[_4, _5]#put[_3, _4]#put[_5, _6]#put[_0, _1]#put[_2, _3]#equivTo[um, nat.ord]]
        assertEquals(map.sorted(nat.ord).put(_4, _5).put(_3, _4).put(_5, _6).put(_0, _1).put(_2, _3), um)
   }

}
