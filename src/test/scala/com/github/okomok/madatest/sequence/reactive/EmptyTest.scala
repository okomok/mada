

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class EmptyTest {
    def testTrivial: Unit = {
        val s = new java.util.ArrayList[Int]
        reactive.empty.of[Int].activate(reactor.make(_ => s.add(99), s.add(_)))
        assertEquals(vector.Of(99), vector.from(s))
    }
}
