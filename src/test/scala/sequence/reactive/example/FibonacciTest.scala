

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest
package reactivetest; package example


import com.github.okomok.mada

    import mada.sequence._
    import junit.framework.Assert._

    class FibonacciTezt {
//    class FibonacciTest extends org.scalatest.junit.JUnit3Suite {
        val t = new java.util.Timer(true)
        def naturals: Reactive[Int] = {
            val s: Reactive[Unit] = reactive.Schedule(t.schedule(_, 0, 1000))
            s.generate(iterative.iterate(0)(_ + 1))
        }
        def testTrivial: Unit = {
            // too many instances.
            def fibs: Reactive[Int] = naturals.take(2) then_++ ((fibs zip fibs.tail).map2(_ + _)).byName
            var answer: Int = 0
            fibs.foreach(println(_))
        }
    }
