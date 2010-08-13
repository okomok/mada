

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._


class StartsWithTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _3 :: _4 :: _5 :: Nil
        val ys: ys = _3 :: _4 :: _5 :: Nil
        type r   = peg.StartsWith.apply[xs, ys]
        val r: r = peg.StartsWith.apply(xs, ys)
        meta.assert[r#isEmpty#not]
        meta.assertSame[ys, r#get#asInstanceOfProduct2#_1#asInstanceOfList#force]
        meta.assertSame[_6 :: Nil, r#get#asInstanceOfProduct2#_2#asInstanceOfList#force]
        assertEquals(ys, r.get.asInstanceOfProduct2._1)
        assertEquals(_6 :: Nil, r.get.asInstanceOfProduct2._2)
    }

    def testNil {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = Nil
        val ys: ys = Nil
        type r   = peg.StartsWith.apply[xs, ys]
        val r: r = peg.StartsWith.apply(xs, ys)
        meta.assert[r#isEmpty#not]
        meta.assertSame[Nil, r#get#asInstanceOfProduct2#_1#asInstanceOfList#force]
        meta.assertSame[xs, r#get#asInstanceOfProduct2#_2#asInstanceOfList#force]
        assertEquals(Nil, r.get.asInstanceOfProduct2._1)
        assertEquals(xs, r.get.asInstanceOfProduct2._2)
    }

    def testNilNil {
        type xs    = Nil
        val xs: xs = Nil
        type ys    = Nil
        val ys: ys = Nil
        type r   = peg.StartsWith.apply[xs, ys]
        val r: r = peg.StartsWith.apply(xs, ys)
        meta.assert[r#isEmpty#not]
        meta.assertSame[Nil, r#get#asInstanceOfProduct2#_1#asInstanceOfList#force]
        meta.assertSame[Nil, r#get#asInstanceOfProduct2#_2#asInstanceOfList#force]
        assertEquals(Nil, r.get.asInstanceOfProduct2._1)
        assertEquals(Nil, r.get.asInstanceOfProduct2._2)
    }

    def testNone {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _5 :: _6 :: Nil
        val ys: ys = _5 :: _6 :: Nil
        type r   = peg.StartsWith.apply[xs, ys]
        val r: r = peg.StartsWith.apply(xs, ys)
        meta.assert[r#isEmpty]
        assertEquals(None, r)
    }

    def testAll {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _3 :: _4 :: _5 :: _6 :: Nil
        val ys: ys = _3 :: _4 :: _5 :: _6 :: Nil
        type r   = peg.StartsWith.apply[xs, ys]
        val r: r = peg.StartsWith.apply(xs, ys)
        meta.assert[r#isEmpty#not]
        meta.assertSame[xs, r#get#asInstanceOfProduct2#_1#asInstanceOfList#force]
        meta.assertSame[Nil, r#get#asInstanceOfProduct2#_2#asInstanceOfList#force]
        assertEquals(xs, r.get.asInstanceOfProduct2._1)
        assertEquals(Nil, r.get.asInstanceOfProduct2._2)
    }

}
