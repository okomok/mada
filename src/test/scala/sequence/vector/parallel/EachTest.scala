

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package vectortest; package paralleltest


import com.github.okomok.mada

import mada.sequence.vector._

import junit.framework.Assert._
import com.github.okomok.madatest.sequencetest.vectortest.detail.Example._
import com.github.okomok.madatest.sequencetest.vectortest.detail._


class EachTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val ex = Array(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14)
        mada.sequence.vector.from(ex).parallel.each(print(_))
        mada.sequence.vector.from(ex).parallelBy(6).each(print(_))
        mada.sequence.vector.from(ex).parallelBy(100).each(print(_))
    }

    def print(i: Int) = {
        //println(i)
        ()
    }
}
