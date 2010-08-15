

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class OrTest extends org.scalatest.junit.JUnit3Suite {

    def testNilInput {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testOneInput {
        type xs    = _3 :: _8 :: _9 :: Nil
        val xs: xs = _3 :: _8 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testParseLeft {
        type xs    = _3 :: _5 :: _9 :: _0 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _0 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assert[r#successful]
        assertTrue(r.successful.undual)
        type e = r#get#asEither
        val e: e = r.get.asEither
        meta.assert[e#isLeft]
        assertTrue(e.isLeft.undual)
        meta.assertSame[_3 :: _5 :: _9 :: Nil, e#get]
        assertEquals(_3 :: _5 :: _9 :: Nil, e.get)
        meta.assertSame[_0 :: Nil, r#next#force]
        assertEquals(_0 :: Nil, r.next)
    }

    def testParseRight {
        type xs    = _4 :: _2 :: _9 :: _0 :: Nil
        val xs: xs = _4 :: _2 :: _9 :: _0 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#or[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).or(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assert[r#successful]
        assertTrue(r.successful.undual)
        type e = r#get#asEither
        val e: e = r.get.asEither
        meta.assert[e#isRight]
        assertTrue(e.isRight.undual)
        meta.assertSame[_4 :: _2 :: Nil, e#get]
        assertEquals(_4 :: _2 :: Nil, e.get)
        meta.assertSame[_9 :: _0 :: Nil, r#next]
        assertEquals(_9 :: _0 :: Nil, r.next)
    }
}
