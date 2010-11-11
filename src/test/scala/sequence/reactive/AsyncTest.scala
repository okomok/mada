

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._


class AsyncTest extends org.scalatest.junit.JUnit3Suite {

    def naturals: Reactive[Int] = {
        reactive.async.generate(0 until 1000)
    }

    def testTrivial {
        val b = new java.util.ArrayList[Int]
        val c = new java.util.concurrent.CountDownLatch(1)

        naturals take {
            100
        } then {
            c.countDown
        } foreach {
            b.add(_)
        }

        c.await
        expect(vector.range(0, 100))(vector.from(b))
    }

    def testRejection {
        val b = new java.util.ArrayList[Int]
        val c = new java.util.concurrent.CountDownLatch(1)

        var _break = false
        while (!_break) {
            try {
                mada.eval.Parallel {
                    Thread.sleep(7)
                }
            } catch {
                case _: mada.eval.RejectedParallelException => _break = true
            }
        }

        naturals take {
            100
        } then {
            c.countDown
        } foreach { x =>
            b.add(x)
        }

        c.await
        expect(vector.range(0, 100))(vector.from(b))
    }

    def testReallyLazyVal {
        def anError = reactive.async.generate(iterative.lazySingle{throw new Error; 999})
        anError take 100
        ()
    }

}
