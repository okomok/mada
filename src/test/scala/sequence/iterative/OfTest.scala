

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package iterativetest


import com.github.okomok.mada

import mada.sequence.iterative
import junit.framework.Assert._


class OfTest extends org.scalatest.junit.JUnit3Suite {
    def testMatching: Unit = {
        val a = iterative.Of(1,2,3,4,5)
        val iterative.Of(x1,2,x3,4,x5) = a
        assertEquals(1, x1)
        assertEquals(3, x3)
        assertEquals(5, x5)

        a match {
            case iterative.Of(x1,x2) => fail("doh")
            case iterative.Of(x1,2,x3,4,x5) => ()
            case _ => fail("doh")
        }

        a match {
            case iterative.Of(1,2,_*) => ()
            case _ => fail("doh")
        }
        ()
    }

}
