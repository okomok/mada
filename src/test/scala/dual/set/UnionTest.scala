

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class UnionTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type m   = set.sorted[nat.ord]#add[_4]#add[_5]#add[_0]
        val m: m = set.sorted(nat.ord).add(_4).add(_5).add(_0)

        type m2 =    set.sorted[nat.ord]#add[_3]#add[_1]#add[_2]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_1).add(_2)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`, set.sorted[nat.ord]#add[_4]#add[_3]#add[_5]#add[_0]#add[_1]#add[_2]#equivTo[um]]
        assertEquals(set.sorted(nat.ord).add(_4).add(_3).add(_5).add(_0).add(_1).add(_2), um)
   }

    def testEmpty {
        type m   = set.sorted[nat.ord]
        val m: m = set.sorted(nat.ord)

        type m2 = set.sorted[nat.ord]
        val m2: m2 = set.sorted(nat.ord)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[set.sorted[nat.ord], um]
        assertEquals(set.sorted(nat.ord), um)
   }

    def testEmpty2 {
        type m   = set.sorted[nat.ord]
        val m: m = set.sorted(nat.ord)

        type m2 =    set.sorted[nat.ord]#add[_3]#add[_1]#add[_2]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_1).add(_2)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`,  set.sorted[nat.ord]#add[_3]#add[_1]#add[_2]#equivTo[um]]
        assertEquals(set.sorted(nat.ord).add(_3).add(_1).add(_2), um)
   }

   def testLeftBiased {
        type m   = set.sorted[nat.ord]#add[_4]#add[_5]#add[_0]
        val m: m = set.sorted(nat.ord).add(_4).add(_5).add(_0)

        type m2 =    set.sorted[nat.ord]#add[_3]#add[_4]#add[_2]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_4).add(_2)

        type um = m#union[m2]
        val um: um = m.union(m2)

        meta.assertSame[`true`,  set.sorted[nat.ord]#add[_4]#add[_3]#add[_5]#add[_0]#add[_2]#equivTo[um]]
        assertEquals(set.sorted(nat.ord).add(_4).add(_3).add(_5).add(_0).add(_2), um)
   }

}