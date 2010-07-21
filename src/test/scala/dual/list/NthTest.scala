

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class NthTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val u: xs#nthOption[_2] = xs.nthOption(_2)
        meta.assertSame[Some[_7], xs#nthOption[_2]]
        assertEquals(Some(_7), u)
    }

    def testTrivialNone {
        type xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val xs: xs = _5 :: _6 :: _7 :: _8 :: _9 :: Nil
        val u: xs#nthOption[_10] = xs.nthOption(_10)
        meta.assertSame[None, xs#nthOption[_10]]
        assertEquals(None, u)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#nthOption[_10] = xs.nthOption(_10)
        meta.assertSame[None, xs#nthOption[_10]]
        assertEquals(None, u)
    }

}
