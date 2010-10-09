

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class PostTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        for (x <- reactive.Of(0,1,2,3,4).post.map(_+1)) {
            s.add(x)
        }
        //assertEquals(vector.Of(1,2,3,4,5), vector.from(s)) // indeterministic
    }

}
