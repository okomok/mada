

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class UnfoldTest extends org.scalatest.junit.JUnit3Suite {
    def testRight: Unit = {
        val r = reactive.unfoldRight(10){ b => if (b == 0) None else Some(b, b-1) }
        val out = new java.util.ArrayList[Int]
        r.foreach(out.add(_))
        assertEquals(iterative.Of(10,9,8,7,6,5,4,3,2,1), iterative.from(out))
    }
}
