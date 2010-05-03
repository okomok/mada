

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class BreakTest {
    def testTrivial: Unit = {
        val r = reactive.Of(1,2,3,4,5,6)
        val out = new java.util.ArrayList[Int]
        r.
            fork{r => r.activate(reactor.make(_ => out.add(98), e => out.add(e *  2)))}.
            fork{r => ()}.
            break.
            fork{r => r.activate(reactor.make(_ => out.add(99), e => out.add(e + 10)))}.
            fork{r => ()}.
            start

        assertEquals(iterative.Of(2,4,6,8,10,12,98), iterative.from(out))
    }
}
