

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.eps


class EpsTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = Nil
        val xs: xs = Nil
        type r = eps#matches[xs]
        val r: r = eps.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testTrivial2 {
        type xs    = _7 :: Nil
        val xs: xs = _7 :: Nil
        type r = eps#matches[xs]
        val r: r = eps.matches(xs)
        meta.assertNot[r]
        assertFalse(r.undual)
    }

    def testParse {
        type xs    = _3 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _5 :: _6 :: Nil
        type r = eps#parse[xs]
        val r: r = eps.parse(xs)
        meta.assert[r#successful]
        meta.assertSame[Nil, r#get]
        meta.assertSame[xs, r#next#force]
        assertEquals(Nil, r.get)
        assertEquals(_3 :: _5 :: _6 :: Nil, r.next)
    }

}
