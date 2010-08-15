

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package pegtest


import com.github.okomok.mada

import mada.dual._
import nat.dense.Literal._
import junit.framework.Assert._
import peg.fromList


class SeqTest extends org.scalatest.junit.JUnit3Suite {

    def testNilInput {
        type xs    = Nil
        val xs: xs = Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#seq[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).seq(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next#force]
        assertEquals(xs, r.next)
    }

    def testFail {
        type xs    = _3 :: _5 :: _9 :: _4 :: _1 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _4 :: _1 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#seq[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).seq(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assertNot[r#successful]
        assertFalse(r.successful.undual)
        meta.assertSame[xs, r#next]
        assertEquals(xs, r.next)
    }

    def testFullMatch {
        type xs    = _3 :: _5 :: _9 :: _4 :: _2 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _4 :: _2 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#seq[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).seq(fromList(_4 :: _2 :: Nil))
        type r = p#matches[xs]
        val r: r = p.matches(xs)
        meta.assert[r]
        assertTrue(r.undual)
    }

    def testSubmatch {
        type xs    = _3 :: _5 :: _9 :: _4 :: _2 :: _1 :: _5 :: Nil
        val xs: xs = _3 :: _5 :: _9 :: _4 :: _2 :: _1 :: _5 :: Nil
        type p   = fromList[_3 :: _5 :: _9 :: Nil]#seq[fromList[_4 :: _2 :: Nil]]
        val p: p = fromList(_3 :: _5 :: _9 :: Nil).seq(fromList(_4 :: _2 :: Nil))
        type r = p#parse[xs]
        val r: r = p.parse(xs)
        meta.assert[r#successful]
        assertTrue(r.successful.undual)
        type e = r#get#asProduct2
        val e: e = r.get.asProduct2
        meta.assertSame[_3 :: _5 :: _9 :: Nil, e#_1]
        assertEquals(_3 :: _5 :: _9 :: Nil, e._1)
        meta.assertSame[_4 :: _2 :: Nil, e#_2]
        assertEquals(_4 :: _2 :: Nil, e._2)
        meta.assertSame[_1 :: _5 :: Nil, r#next]
        assertEquals(_1 :: _5 :: Nil, r.next)
    }

}
