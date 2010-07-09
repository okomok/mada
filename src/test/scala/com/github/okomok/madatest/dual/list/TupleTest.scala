

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._


/*
class TupleTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testFrom {
        val t: (Int, String, java.lang.Integer) = (3, "hello", new java.lang.Integer(10))

        type l = Int :: String :: java.lang.Integer :: Nil
        val l: l = list.fromTuple(t)

        assertSame(t._2, l.nth(_1))
    }

    def testTo {
        type l = Int :: String :: java.lang.Integer :: Nil
        val l: l = 3 :: "hello" :: new java.lang.Integer(10):: Nil

        val t: (Int, String, java.lang.Integer) = l.toTuple

        assertSame(t._2, l.nth(_1))
    }
}
*/
