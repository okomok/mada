

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada
import mada.sequence._
import reactive.Reactor
import junit.framework.Assert._
import scala.actors.Actor


class DropUntilTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val out = new java.util.ArrayList[Int]

        val b = Reactor.singleThreaded()
        val a = Reactor.singleThreaded { r =>
            r collect {
                case e: Int => e
            } dropUntil {
                b
            } react { x =>
                out.add(x)
            } start
        }

        a ! 1
        a ! 2
        a ! 3
        b ! "go"
        a ! 4
        a ! 5
        assertEquals(iterative.Of(4,5), iterative.from(out))
    }

}
