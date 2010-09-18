

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package sequencetest; package reactivetest


import com.github.okomok.mada

import mada.sequence._
import junit.framework.Assert._


import scala.actors.Actor


class ActorTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val r = reactive.Of(1,2,3,4,5)
        val out = new java.util.ArrayList[Int]

        case object OK
        val cur = Actor.self

        val a =
            r take {
                3
            } then {
                cur ! OK
                Actor.exit
            } doing { x =>
                out.add(x)
            } doing { x =>
                out.add(x)
            } actor

        Actor.receive {
            case OK =>
        }

        assertEquals(iterative.Of(1,1,2,2,3,3), iterative.from(out))
    }
}
