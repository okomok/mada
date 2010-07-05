

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class FilterTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    class not2 extends Function1 {
        override  def apply[x <: Any](x: x): apply[x] = x.asInstanceOfNat !== _2N
        override type apply[x <: Any] = x#asInstanceOfNat# !==[_2N]
    }
    val not2 = new not2

    def testTrivial {
        type xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: Nil
        val xs: xs = _2N :: _3N :: _4N :: _2N :: _5N :: _6N :: _2N :: Nil
        val u: xs#filter[not2] = xs.filter(not2)
        val v: _3N :: _4N :: _5N :: _6N :: Nil = u
        assertEquals(_3N :: _4N :: _5N :: _6N :: Nil, v)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil
        val u: xs#filter[not2] = xs.filter(not2)
        val v: Nil = u
        ()
    }
}

