

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class MatchTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        type l = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Nil
        val l: l = Box(3) :: Box("hello") :: Box(new java.lang.Integer(10)) :: Nil

        l match {
            case Box(x) :: Box(y) :: Box(z) :: Nil => {
                assertEquals(3, x + 1 - 1)
                assertEquals("hello", y)
            }
            case Box(x) :: ys => {
                assertEquals(3, x + 1 - 1)
                val _l: Box[String] :: Box[java.lang.Integer] :: Nil = ys
                _l match {
                    case Box(y) :: zs => {
                        assertEquals("hello", y)
                    }
                    case _ => fail("doh")
                }
            }
            case _ => fail("doh")
        }

        ()
    }

    def testNil: Unit = {
        Nil match {
            case Nil => ()
            case _ => fail("doh")
        }
        ()
    }
}
