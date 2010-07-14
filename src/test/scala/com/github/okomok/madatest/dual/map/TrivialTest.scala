

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class TrivialTest extends junit.framework.TestCase {

    def testSingle {
        type o = ordering.ofNatPeano
        val o: o = ordering.ofNatPeano
        type s = map.single[_3, box[Int], o]
        val s: s = map.single(_3, box(3), o)

        AssertInvariant(s)

        meta.assertSame[_1, s#size]
        meta.assertSame[_3, s#key]
        meta.assertSame[box[Int], s#value]
        meta.assertSame[map.Nil[o], s#left]
        meta.assertSame[map.Nil[o], s#right]
        meta.assertSame[o, s#ord]
        ()
    }

    def testPut {
        type o = ordering.ofNatPeano
        val o: o = ordering.ofNatPeano

        type m = map.Nil[o]#put[_3, box[Int]]#put[_5, box[Char]]#put[_1, box[String]]
        val m: m = map.Nil(o).put(_3, box(3)).put(_5, box('c')).put(_1, box("wow"))

        AssertInvariant(m)

        meta.assertSame[_3, m#size]

        type v8 = m#get[_8]
        val v8: v8 = m.get(_8)
        meta.assertSame[None, v8]

        type v5 = m#get[_5]#get
        val v5: v5 = m.get(_5).get
        meta.assertSame[box[Char], v5]
        assertEquals('c', v5.undual)
    }

    def testContains {
        type o = ordering.ofNatPeano
        val o: o = ordering.ofNatPeano

        type m = map.Nil[o]#put[_3, box[Int]]#put[_5, box[Char]]#put[_1, box[String]]
        val m: m = map.Nil(o).put(_3, box(3)).put(_5, box('c')).put(_1, box("wow"))

        meta.assertSame[`false`, m#contains[_9]]
        meta.assertSame[`true`, m#contains[_5]]
    }

}
