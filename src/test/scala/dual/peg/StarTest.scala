

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class StarTest extends org.scalatest.junit.JUnit3Suite {

    def testNilInput {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#star
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).star
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testOneInput {
        type xs    = _3 :: _5 :: _9 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#star
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).star
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testLongInput {
        type xs    = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#star
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).star
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

}
