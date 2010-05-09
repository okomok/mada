

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta


class ElementOfTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        val lst1 = 3.1 :: "hello" :: i :: 9 :: 'a' :: 12 :: Nil

        val e1: Int = lst1.elementOf[Int]
        assertEquals(9, e1)

        val e2 = lst1.elementOf[java.lang.Integer]
        assertSame(i, e2)

        // This doesn't complie, in other words, meta-throw.
        // val e3: scala.List[Int] = lst1.elementOf[scala.List[Int]]

        ()
    }
}
