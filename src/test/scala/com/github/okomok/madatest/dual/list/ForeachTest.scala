

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class ForeachTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)
    def testTrivial2 {
    }
/*
    def testTrivial {
        type xs = Box[Int] :: Box[String] :: Box[Char] :: Nil
        val xs: xs = Box(3) :: Box("hello") :: Box('a') :: Nil

        val r = new java.util.ArrayList[Any]
        class AddString extends Function1 {
            override def apply[x <: Any](x: x) = r.add(x.toString)
        }
        val u: Unit = xs.foreach(new AddString)

        assertEquals("[3, hello, a]", r.toString)
    }

    def testTrivialNil {
        type xs = Nil
        val xs: xs = Nil

        val r = new java.util.ArrayList[Any]
        class AddString extends Function1 {
            override def apply[x <: Any](x: x) = r.add(x.toString)
        }
        val u: Unit = xs.foreach(new AddString)

        assertTrue(r.isEmpty)
    }
*/
}

