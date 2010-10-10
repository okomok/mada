

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest;
package sequencetest; package reactivetest; package example


import com.github.okomok.mada
import mada.sequence._

class NaturalsTezt {
//class NaturalsTest extends org.scalatest.junit.JUnit3Suite {

    def naturals: Reactive[Int] = {
        val t = new java.util.Timer(true)
        val s: Reactive[Unit] = reactive.Schedule(t.schedule(_, 0, 1000))
        s.replace(iterative.iterate(0)(_ + 1))
    }

    def testTrivial {
        naturals.take(10).foreach(println(_))
        Thread.sleep(12000)
    }

}
