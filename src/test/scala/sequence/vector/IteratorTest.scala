

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import mada.sequence.vector.from
import junit.framework.Assert._
import detail.Sample._


class IteratorTest extends org.scalatest.junit.JUnit3Suite {
    def testTo: Unit = {
        val it = from(sample1).toSeq.iterator
        var i = 0
        it.foreach({ (e: Int) =>
            assertEquals(sample1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = iterative.from(sample1).toVector
        detail.TeztVectorReadOnly(sample1, ac)
    }
}
