

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.dot


class DotTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _3 :: Nil
        val xs: xs = _3 :: Nil
        type r = dot#matches[xs]
        val r: r = dot.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testTrivial2 {
        type xs    = _7 :: Nil
        val xs: xs = _7 :: Nil
        type r = dot#matches[xs]
        val r: r = dot.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testFail {
        type xs    = Nil
        val xs: xs = Nil
        type r = dot#matches[xs]
        val r: r = dot.matches(xs)
        meta.assert[r#not]
        assertFalse(r.undual)
    }

    def testParse {
        type xs    = _3 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _5 :: _6 :: Nil
        type r = dot#parse[xs]
        val r: r = dot.parse(xs)
        meta.assert[r#successful]
        meta.assertSame[_3, r#get]
        meta.assertSame[_5 :: _6 :: Nil, r#next#force]
        assertEquals(_3, r.get)
        assertEquals(_5 :: _6 :: Nil, r.next)
    }

}
