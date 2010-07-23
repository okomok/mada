

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class EquivToTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type m   = set.sorted[nat.ord]#add[_4]#add[_3]#add[_1]#add[_2]#add[_5]#add[_0]
        val m: m = set.sorted(nat.ord).add(_4).add(_3).add(_1).add(_2).add(_5).add(_0)

        type m2   = set.sorted[nat.ord]#add[_3]#add[_4]#add[_0]#add[_2]#add[_5]#add[_1]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_4).add(_0).add(_2).add(_5).add(_1)

        meta.assertSame[`true`, m#equivTo[m]]
        meta.assertSame[`true`, m#equivTo[m2]]
        meta.assertSame[`true`, m2#equivTo[m]]
        assertEquals(`true`, m.equivTo(m2))
    }

    def testTrivial2 {
        type m   = set.sorted[nat.ord]#add[_4]#add[_8]#add[_1]#add[_2]#add[_5]#add[_0]
        val m: m = set.sorted(nat.ord).add(_4).add(_8).add(_1).add(_2).add(_5).add(_0)

        type m2   = set.sorted[nat.ord]#add[_3]#add[_4]#add[_0]#add[_2]#add[_5]#add[_1]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_4).add(_0).add(_2).add(_5).add(_1)

        meta.assertSame[`false`, m#equivTo[m2]]
        meta.assertSame[`false`, m2#equivTo[m]]
        assertEquals(`false`, m.equivTo(m2))
    }

    def testTrivialDifferentSize {
        type m   = set.sorted[nat.ord]#add[_4]#add[_3]#add[_1]#add[_2]#add[_5]
        val m: m = set.sorted(nat.ord).add(_4).add(_3).add(_1).add(_2).add(_5)

        type m2   = set.sorted[nat.ord]#add[_3]#add[_4]#add[_0]#add[_2]#add[_5]#add[_1]
        val m2: m2 = set.sorted(nat.ord).add(_3).add(_4).add(_0).add(_2).add(_5).add(_1)

        meta.assertSame[`false`, m#equivTo[m2]]
        meta.assertSame[`false`, m2#equivTo[m]]
        assertEquals(`false`, m.equivTo(m2))
    }

    def testTrivialEmpty {
        type m   = set.sorted[nat.ord]
        val m: m = set.sorted(nat.ord)

        type m2   = set.sorted[nat.ord]
        val m2: m2 = set.sorted(nat.ord)

        meta.assertSame[`true`, m#equivTo[m]]
        meta.assertSame[`true`, m#equivTo[m2]]
        meta.assertSame[`true`, m2#equivTo[m]]
        assertEquals(`true`, m.equivTo(m2))
    }

}
