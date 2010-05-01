

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package listtest


import com.github.okomok.mada

import mada.sequence.list
import junit.framework.Assert._


class OfTest {
    def testMatching: Unit = {
        val a = list.Of(1,2,3,4,5)
        val list.Of(x1,2,x3,4,x5) = a
        assertEquals(1, x1)
        assertEquals(3, x3)
        assertEquals(5, x5)

        a match {
            case list.Of(x1,x2) => fail("doh")
            case list.Of(x1,2,x3,4,x5) => ()
            case _ => fail("doh")
        }

        a match {
            case list.Of(1,2,_*) => ()
            case _ => fail("doh")
        }
        ()
    }

}
