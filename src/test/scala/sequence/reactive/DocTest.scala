

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


    import com.github.okomok.mada
    import mada.sequence._
    import reactive.Reactor
    import junit.framework.Assert._
    import scala.actors.Actor

    class DocTest extends org.scalatest.junit.JUnit3Suite {
        def testTrivial: Unit = {
            val out = new java.util.ArrayList[Int]

            case object OK
            val cur = Actor.self

            val a = new Reactor
            // build an Actor using Reactive combinators.
            a collect {
                case e: Int => e + 10
            } drop {
                1
            } take {
                3
            } then {
                cur ! OK
                Actor.exit
            } doing { x =>
                out.add(x)
            } start

            a ! 0
            a ! 1
            a ! "ignored"
            a ! 2
            a ! 3
            Actor.receive { case OK => }
            assertEquals(iterative.Of(11,12,13), iterative.from(out))
        }
    }
