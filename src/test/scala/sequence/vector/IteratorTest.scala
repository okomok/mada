

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest


import com.github.okomok.mada

import mada.sequence._
import mada.sequence.vector.from
import junit.framework.Assert._
import detail.Example._


class IteratorTest extends org.scalatest.junit.JUnit3Suite {
    def testTo: Unit = {
        val it = from(example1).toSeq.iterator
        var i = 0
        it.foreach({ (e: Int) =>
            assertEquals(example1(i), e)
            i += 1
        })
    }

    def testFrom: Unit = {
        val ac = iterative.from(example1).toVector
        detail.TeztVectorReadOnly(example1, ac)
    }
}
