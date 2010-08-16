

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.{assert => dassert}
import nat.dense.Literal._
import junit.framework.Assert._


class TimesTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs = _1 :: _2 :: _3 :: Nil
        val xs: xs = _1 :: _2 :: _3 :: Nil
        type rs = xs#times[_3]
        val rs: rs = xs.times(_3)
        free.assert[rs#equalWith[_1 :: _2 :: _3 :: _1 :: _2 :: _3 :: _1 :: _2 :: _3 :: Nil, nat.naturalOrdering]]
        assertEquals(_1 :: _2 :: _3 :: _1 :: _2 :: _3 :: _1 :: _2 :: _3 :: Nil, rs)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type rs = xs#times[_3]
        val rs: rs = xs.times(_3)
        free.assertSame[Nil, rs#force]
        assertEquals(Nil, rs)
    }

    def testTrivialZero {
        type xs = _1 :: _2 :: _3 :: Nil
        val xs: xs = _1 :: _2 :: _3 :: Nil
        type rs = xs#times[_0]
        val rs: rs = xs.times(_0)
        free.assertSame[Nil, rs#force]
        assertEquals(Nil, rs)
    }

}
