

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package nattest; package densetest


import com.github.okomok.mada

import mada.dual._
import mada.dual.nat.dense.Literal._
import junit.framework.Assert._


class DivModTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type n = _3#divMod[_5]
        val n: n = _3.divMod(_5)
        free.assertSame[Tuple2[_0, _3], n]
        assertEquals(Tuple2(_0, _3), n)
    }

    def testTrivial2 {
        type n = _13#divMod[_5]
        val n: n = _13.divMod(_5)
        free.assertSame[Tuple2[_2, _3], n]
        assertEquals(Tuple2(_2, _3), n)
    }

    def testTrivial3 {
        type n = _15#divMod[_5]
        val n: n = _15.divMod(_5)
        free.assertSame[Tuple2[_3, _0], n]
        assertEquals(Tuple2(_3, _0), n)
    }

    def testTrivial4 {
        type n = _14#div[_3]
        val n: n = _14 div _3
        free.assertSame[_4, n]
        assertEquals(_4, n)
    }

    def testTrivial5 {
        type n = _14#mod[_3]
        val n: n = _14 mod _3
        free.assertSame[_2, n]
        assertEquals(_2, n)
    }

    def testTrivial6 {
        type n = _1#divMod[_1]
        val n: n = _1.divMod(_1)
        free.assertSame[Tuple2[_1, _0], n]
        assertEquals(Tuple2(_1, _0), n)
    }

    def testTrivial7 {
        type n = _15#divMod[_1]
        val n: n = _15.divMod(_1)
        free.assertSame[Tuple2[_15, _0], n]
        assertEquals(Tuple2(_15, _0), n)
    }

}
