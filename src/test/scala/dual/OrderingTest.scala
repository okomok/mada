

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._
import nat.peano.Literal._
import nat.Peano


class OrderingTest extends org.scalatest.junit.JUnit3Suite {

    def testLT {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#compare[_1, _3]
        val r: r = o.compare(_1, _3)
        meta.assertSame[ordering.LT, r]
        assertEquals(-1, r.undual)
        assertSame(ordering.LT, r)
        assertEquals(ordering.LT, r)
    }

    def testGT {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#compare[_4, _2]
        val r: r = o.compare(_4, _2)
        meta.assertSame[ordering.GT, r]
        assertEquals(1, r.undual)
        assertSame(ordering.GT, r)
        assertEquals(ordering.GT, r)
    }

    def testEQ {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#compare[_5, _5]
        val r: r = o.compare(_5, _5)
        meta.assertSame[ordering.EQ, r]
        assertEquals(0, r.undual)
        assertSame(ordering.EQ, r)
        assertEquals(ordering.EQ, r)
    }

    def testMatchLT {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#`match`[_5, _6, Const0[_0], Const0[_1], Const0[_2]]
        val r: r = o.`match`(_5, _6, Const0(_0), Const0(_1), Const0(_2))
        meta.assertSame[_0, r]
        assertEquals(_0, r)
    }

    def testMatchGT {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#`match`[_7, _6, Const0[_0], Const0[_1], Const0[_2]]
        val r: r = o.`match`(_7, _6, Const0(_0), Const0(_1), Const0(_2))
        meta.assertSame[_1, r]
        assertEquals(_1, r)
    }

    def testMatchEQ {
        type o = nat.ord
        val o: o = nat.ord

        type r = o#`match`[_7, _7, Const0[_0], Const0[_1], Const0[_2]]
        val r: r = o.`match`(_7, _7, Const0(_0), Const0(_1), Const0(_2))
        meta.assertSame[_2, r]
        assertEquals(_2, r)
    }

}
