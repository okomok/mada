

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


class TimerTest extends org.scalatest.junit.JUnit3Suite {

    def naturals: Reactive[Int] = {
        val t = new java.util.Timer(true)
        val s: Reactive[Unit] = reactive.Schedule(t.schedule(_, 0, 100))
        s.generate(iterative.iterate(0)(_ + 1))
    }

    def testRecursive: Unit = {
        val b = new java.util.ArrayList[Int]
        def rx: Reactive[Int] = naturals.take(3).then_++(rx)
        rx.take(10).foreach(b.add(_))
        Thread.sleep(2000)
        assertEquals(vector.Of(0,1,2,0,1,2,0,1,2,0), vector.from(b))
    }

}
