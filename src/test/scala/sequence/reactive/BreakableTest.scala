

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._


class BreakableTest extends org.scalatest.junit.JUnit3Suite {

    def naturals: Reactive[Int] = {
        reactive.origin(mada.eval.Async).generate(0 until 1000)
    }

    def testTrivial {
        val b = new java.util.ArrayList[Int]
        val c = new java.util.concurrent.CountDownLatch(1)

        naturals.breakable.foreach { case (x, break) =>
            if (x == 50) {
                c.countDown()
                break()
            } else {
                b.add(x)
            }
        }

        c.await()
        expect(vector.range(0, 50))(vector.from(b))
    }

}
