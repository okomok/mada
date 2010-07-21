

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    def testSingle {
        type o = nat.ord
        val o: o = nat.ord
        type s = map.sorted[o]#put[_3, box[Int]]
        val s: s = map.sorted(o).put(_3, box(3))

        AssertInvariant(s)

        meta.assertSame[nat.dense._1, s#size]
        meta.assertSame[_3, s#key]
        meta.assertSame[box[Int], s#value]
        meta.assertSame[map.sorted[o], s#left]
        meta.assertSame[map.sorted[o], s#right]
        meta.assertSame[o, s#ord]
        ()
    }

    def testPut {
        type o = nat.ord
        val o: o = nat.ord

        type m = map.sorted[o]#put[_3, box[Int]]#put[_5, box[Char]]#put[_1, box[String]]
        val m: m = map.sorted(o).put(_3, box(3)).put(_5, box('c')).put(_1, box("wow"))

        AssertInvariant(m)

        meta.assertSame[nat.dense._3, m#size]

        type v8 = m#get[_8]
        val v8: v8 = m.get(_8)
        meta.assertSame[None, v8]

        type v5 = m#get[_5]#get
        val v5: v5 = m.get(_5).get
        meta.assertSame[box[Char], v5]
        assertEquals('c', v5.undual)
    }

    def testContains {
        type o = nat.ord
        val o: o = nat.ord

        type m = map.sorted[o]#put[_3, box[Int]]#put[_5, box[Char]]#put[_1, box[String]]
        val m: m = map.sorted(o).put(_3, box(3)).put(_5, box('c')).put(_1, box("wow"))

        meta.assertSame[`false`, m#contains[_9]]
        meta.assertSame[`true`, m#contains[_5]]
    }

    def testUndual {
        type m   = map.sorted[nat.ord]#put[_3, _4]#put[_1, _2]#put[_5, _6]
        val m: m = map.sorted(nat.ord).put(_3, _4).put(_1, _2).put(_5, _6)
        assertEquals(Predef.Map(1 -> 2, 3 -> 4, 5 -> 6), m.undual)
    }

}
