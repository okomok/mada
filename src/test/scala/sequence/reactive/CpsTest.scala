

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada.sequence._
import junit.framework.Assert._


class CpsTest extends org.scalatest.junit.JUnit3Suite {

    val t = new java.util.Timer(true)
    def naturals: Reactive[Int] = {
        val s: Reactive[Unit] = reactive.Schedule(t.schedule(_, 0, 100))
        s.generate(iterative.iterate(0)(_ + 1))
    }

    def testTrivial {
        val arr = new java.util.ArrayList[(Int, Int)]
        reactive.block {
            val x = naturals.take(2).each // 0, 1
            val y = naturals.take(3).each // 0, 1, 2
            arr.add((x, y))
            ()
        }
        Thread.sleep(1200)
        assertEquals(Vector((0,0), (0,1), (0,2), (1,0), (1,1), (1,2)), Vector.from(arr).sort)
    }
}
