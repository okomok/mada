

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import mada.dual.{assert => dassert}
import nat.dense.Literal._
import junit.framework.Assert._


class RangeTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type rs = list.range[_3, _10]
        val rs: rs = list.range(_3, _10)
        meta.assert[rs#equalWith[_3 :: _4 :: _5 :: _6 :: _7 :: _8 :: _9 :: Nil, nat.naturalOrdering]]
        assertEquals(_3 :: _4 :: _5 :: _6 :: _7 :: _8 :: _9 :: Nil, rs)
    }

    def testEmpty {
        type rs = list.range[_10, _10]
        val rs: rs = list.range(_10, _10)
        meta.assert[rs#isEmpty]
        assertEquals(Nil, rs)
    }

}
