

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class IsSortedTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil
        val xs: xs = _2 :: _6 :: _7 :: _8 :: _9 :: _9 :: _10 :: Nil
        type u   = xs#isSorted[nat.ord]
        val u: u = xs.isSorted(nat.ord)
        meta.assert[u]
        assertEquals(`true`, u)
    }

    def testTrivialOne {
        type xs = _5 :: Nil
        val xs: xs = _5 :: Nil
        type u   = xs#isSorted[nat.ord]
        val u: u = xs.isSorted(nat.ord)
        meta.assert[u]
        assertEquals(`true`, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        type u   = xs#isSorted[nat.ord]
        val u: u = xs.isSorted(nat.ord)
        meta.assert[u]
        assertEquals(`true`, u)
    }

    def testTrivialNot {
        type xs    = _2 :: _6 :: _7 :: _5 :: _9 :: _9 :: _10 :: Nil
        val xs: xs = _2 :: _6 :: _7 :: _5 :: _9 :: _9 :: _10 :: Nil
        type u   = xs#isSorted[nat.ord]
        val u: u = xs.isSorted(nat.ord)
        meta.assertNot[u]
        assertEquals(`false`, u)
    }

}
