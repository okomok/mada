

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package listtest


import com.github.okomok.mada

import mada.dual._


class ElementOfTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial {

    /* // hmm, compiler will hung.
        val e1: _3N = Samples.xs_0_5.elementOf[_3N]
        assertEquals(_3N, e1)
     */
        // This doesn't complie, in other words, meta-throw.
        // val e3: scala.List[Int] = lst1.elementOf[scala.List[Int]]

        val e1: Box[Char] = (Box(3) :: Box('c') :: Box("wow") :: Nil).elementOf[Box[Char]]
        assertEquals(Box('c'), e1)
        ()
    }
}
