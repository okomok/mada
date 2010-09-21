

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada
import mada.sequence._
import reactive.Reactor
import junit.framework.Assert._
import scala.actors.Actor


class ReactorTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial: Unit = {
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a = new Reactor
        a.reactive collect {
            case e: Int => e
        } take {
            3
        } then {
            cur ! OK
            Actor.exit
        } doing { x =>
            out.add(x)
        } doing { x =>
            out.add(x+10)
        } start

        a ! 1
        a ! "ignored"
        a ! 2
        a ! 3
        Actor.receive {
            case OK =>
        }
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
    }

    def testSingleThreaded: Unit = {
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a = new Reactor(new scala.actors.scheduler.SingleThreadedScheduler)
        a.reactive collect {
            case e: Int => e
        }  take {
            3
        } then {
            cur ! OK
            Actor.exit
        } doing { x =>
            out.add(x)
        } doing { x =>
            out.add(x+10)
        } start

        a ! 1
        a ! 2
        a ! "ignored"
        a ! 3
        Actor.receive {
            case OK =>
        }
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
    }

    def testSignal {
        import scala.actors.Actor
        val cur = Actor.self
        var answer: Option[Int] = None
        case object OK
        val a = new reactive.Reactor
        val b = new reactive.Reactor
        a.reactive.zip(b.reactive).
            collect{ case (x: Int, y: Int) => x + y }.
            foreach{ sum => answer = Some(sum); cur ! OK }
        a ! 7
        b ! 35
        Actor.receive { case OK => }
        assertEquals(42, answer.get)
    }
}
