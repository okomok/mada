

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class SliceTest extends junit.framework.TestCase {
    def testTrivial: Unit = {
        val t = reactive.Of(1,2,3,4,5,6,7,8)
        val s = new java.util.ArrayList[Int]
        t.slice(1, 5).activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(2,3,4,5, 99), vector.from(s))
    }
}
