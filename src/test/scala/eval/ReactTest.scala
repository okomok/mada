

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package evaltest


import com.github.okomok.mada

import mada.eval
import scala.actors.Actor


class ReactTest extends org.scalatest.junit.JUnit3Suite {

    object Exit

    class MyActor extends Actor {
        override def act = {
            Actor.loop {
                react {
                    case eval.Reaction(f) => f()
                    case Exit => Actor.exit
                }
            }
        }
    }

    def testTrivial {
        val a = new MyActor
        a.start
        val f = eval.React.in(a) {
            Thread.sleep(100)
            999
        }
        expect(999)(f())
        a ! Exit
    }
}
