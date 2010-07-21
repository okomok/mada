

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class MatcherTest extends org.scalatest.junit.JUnit3Suite {

    def testLazy: Unit = {
        val x :: xs = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2 #:: 3 #:: 4 #:: 5 #:: Nil, xs())
    }

    def testStrict: Unit = {
        val x #:: y #:: ys = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2, y)
        assertEquals(3 #:: 4 #:: 5 #:: Nil, ys)
    }

    def testJumble: Unit = {
        val x #:: y #:: (z :: zs) = 1 #:: 2 #:: 3 #:: 4 #:: 5 #:: Nil
        assertEquals(1, x)
        assertEquals(2, y)
        assertEquals(3, z)
        assertEquals(4 #:: 5 #:: Nil, zs())
    }

}
