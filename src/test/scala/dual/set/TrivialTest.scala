

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package settest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    def testAdd {
        type o = nat.ord
        val o: o = nat.ord

        type m = set.sorted[o]#add[_3]#add[_5]#add[_1]
        val m: m = set.sorted(o).add(_3).add(_5).add(_1)

        meta.assertSame[nat.dense._3, m#size]

        type v8 = m#contains[_8]
        val v8: v8 = m.contains(_8)
        meta.assertSame[`false`, v8]

        type v5 = m#contains[_5]
        val v5: v5 = m.contains(_5)
        meta.assertSame[`true`, v5]
    }

    def testContains {
        type o = nat.ord
        val o: o = nat.ord

        type m = set.sorted[o]#add[_3]#add[_5]#add[_1]
        val m: m = set.sorted(o).add(_3).add(_5).add(_1)

        meta.assertSame[`false`, m#contains[_9]]
        meta.assertSame[`true`, m#contains[_5]]
    }

    def testUndual {
        type o = nat.ord
        val o: o = nat.ord

        type m = set.sorted[o]#add[_3]#add[_5]#add[_1]
        val m: m = set.sorted(o).add(_3).add(_5).add(_1)

        assertEquals(Predef.Set(1, 3, 5), m.undual)
    }

}
