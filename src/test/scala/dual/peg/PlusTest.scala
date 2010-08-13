

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class PlusTest extends org.scalatest.junit.JUnit3Suite {

    def testNilInput {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#plus
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).plus
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assertNot[r]
        assertFalse(r.undual)
    }

    def testOneInput {
        type xs    = _3 :: _5 :: _9 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#plus
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).plus
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testLongInput {
        type xs    = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#plus
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).plus
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testParseNoConsume {
        type xs    = _4 :: _3 :: _5 :: _9 :: Nil
        val xs: xs = _4 :: _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#plus
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).plus
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testParseConsume {
        type xs    = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _10 :: _11 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _3 :: _5 :: _9 :: _10 :: _11 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#plus
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).plus
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assert[r#successful]
        assertTrue(r.successful.undual)
        meta.assertSame[(_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: Nil, r#get#force]
        assertEquals((_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: (_3 :: _5 :: _9 :: Nil) :: Nil, r.get)
        meta.assertSame[_10 :: _11 :: Nil, r#next#force]
        assertEquals(_10 :: _11 :: Nil, r.next)
    }

}
