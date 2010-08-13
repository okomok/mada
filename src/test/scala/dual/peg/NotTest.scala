

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class NotTest extends org.scalatest.junit.JUnit3Suite {

    def testNil {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#not
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).not
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testTrivial {
        type xs    = _4 :: _3 :: _5 :: _9 :: Nil
        val xs: xs = _4 :: _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#not
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).not
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assert[r#successful]
        assertTrue(r.successful.undual)
        meta.assertSame[Nil, r#get#force]
        assertEquals(Nil, r.get)
        meta.assertSame[xs, r#next]
        assertEquals(xs, r.next)
    }

    def testFail {
        type xs    = _3 :: _5 :: _9 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#not
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).not
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next]
        assertEquals(xs, r.next)
    }

    def testFail2 {
        type xs    = _3 :: _5 :: _9 :: _4 :: _1 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _4 :: _1 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#not
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).not
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next]
        assertEquals(xs, r.next)
    }

    def testNilNil {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[Nil]#not
        val p: p = fromList(Nil).not
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
    }

}
