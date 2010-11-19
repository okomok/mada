

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

        val a = Reactor { r =>
            r collect {
                case e: Int => e
            } take {
                3
            } onEnd {
                cur ! OK
            } react { x =>
                out.add(x)
            } react { x =>
                out.add(x+10)
            } start
        }

        a ! 1
        a ! "ignored"
        a ! 2
        a ! 3
        Actor.receive {
            case OK =>
        }
        a ! "abandoned"
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
    }

    def testSingleThreaded: Unit = {
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a = Reactor.singleThreaded { r =>
            r collect {
                case e: Int => e
            } take {
                3
            } onEnd {
                cur ! OK
            } react { x =>
                out.add(x)
            } react { x =>
                out.add(x+10)
            } start
        }

        a ! 1
        a ! "ignored"
        a ! 2
        a ! 3
        Actor.receive {
            case OK =>
        }
        a ! "abandoned"
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
    }

    def testRestart: Unit = {
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a = Reactor.singleThreaded { r =>
            r collect {
                case e: Int => e
            } take {
                3
            } onEnd {
                cur ! OK
                Actor.exit
            } react { x =>
                out.add(x)
            } react { x =>
                out.add(x+10)
            } start
        }

        a ! 1
        a ! "ignored"
        a ! 2
        a ! 3
        Actor.receive {
            case OK =>
        }
        assertEquals(Actor.State.Terminated, a.getState)
        a ! "abandoned"
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
        out.clear

        a.restart
        a ! 1
        a ! "ignored"
        a ! 2
        a ! 3
        Actor.receive {
            case OK =>
        }
        assertEquals(Actor.State.Terminated, a.getState)
        a ! "abandoned"
        assertEquals(iterative.Of(1,11,2,12,3,13), iterative.from(out))
    }

    def testEmpty {
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a = Reactor.singleThreaded { r =>
            r collect {
                case e: Int => e
            } take {
                0
            } onEnd {
                cur ! OK
            } start
        }

        Actor.receive {
            case OK =>
        }
    }

    /*
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

    def testSignalSingleThreaded {
        import scala.actors.Actor
        val cur = Actor.self
        var answer: Option[Int] = None
        case object OK
        val a = new reactive.Reactor(new scala.actors.scheduler.SingleThreadedScheduler)
        val b = new reactive.Reactor(new scala.actors.scheduler.SingleThreadedScheduler)
        a.reactive.zip(b.reactive).
            collect{ case (x: Int, y: Int) => x + y }.
            foreach{ sum => answer = Some(sum); cur ! OK }
        a ! 7
        b ! 35
        Actor.receive { case OK => }
        assertEquals(42, answer.get)
    }
    */
}
