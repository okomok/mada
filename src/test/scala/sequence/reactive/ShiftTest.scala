

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class ShiftTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).shift(k => {s.add(99);k();k()}).map(_+1)) {
            s.add(x)
        }
        assertEquals(vector.Of(99,1,1,99,2,2,99,3,3,99,4,4,99,5,5), vector.from(s))
    }

    def testSwing(off: Int) {
        val s = new java.util.ArrayList[Int]
        reactive.Of(0,1,2,3,4) shift { k =>
            javax.swing.SwingUtilities.invokeLater(k)
        } map { x =>
            x + 1
        } foreach { x =>
            s.add(x)
        }
        assertEquals(vector.Of(1,2,3,4,5), vector.from(s))
    }

}
