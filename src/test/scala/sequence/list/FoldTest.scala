

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class FoldTest extends org.scalatest.junit.JUnit3Suite {

    def testRight: Unit = {
        val A: List[Int] = (1 :: 3 :: 4 :: Nil).cycle // infinite
        val B: List[Int] = A.foldRight(Nil.of[Int])(new Cons(_, _))
        assertEquals(A.take(30), B.take(30))
    }

    def testRight2: Unit = {
        val A: List[Int] = (1 :: 3 :: 4 :: Nil).cycle // infinite
        val B: List[Int] = (A :\ Nil.of[Int]) { new Cons(_, _) }
        assertEquals(A.take(30), B.take(30))
    }
}
