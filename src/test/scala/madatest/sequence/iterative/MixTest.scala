

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest.iterativetest


import mada.sequence.iterative._
import junit.framework.Assert._


class MixTest {
    def testTrivial: Unit = {
        val t = Of(1,2,3)
        val m = t.mix(mixin.force)
        val k = m.map(_ + 1).map(_ + 2).map(_ + 3)
        val u = Of(7,8,9)

        val Of(7,8,9) = u

//        println(k.toString)
        k match {
            // expression is implementation defined: especially under Forwarder.
            case Mix(Force(Of(4,5,6)) Map f, x) => ()
            case _ => fail
        }

        assertEquals(u, k)
        assertEquals(u, k)
    }

    def testExpr: Unit = {
        val t = Of(1,2,3)
        val m = Expr(t)
        val k = m.map(_ + 1).map(_ + 2).slice(0,2).map(_ + 3)
        val u = Of(7,8)

        k match {
            case Expr(Map(Slice(Map(Map(Of(1,2,3), f), g), 0,2), h)) => ()
            case _ => fail
        }
        //println(k)
        //println(Eval(k))
        ()
        //assertEquals(u, k)
    }

    def testSeal: Unit = {
        val t = Of(1,2,3)
        val m = t.mix(mixin.seal)
        val k = m.map(_ + 1).map(_ + 2).slice(0,2).map(_ + 3)
        val u = Of(7,8)

/*
        println(t.mix(mixin.seal).slice(0,2))
        println(t.seal.slice(0,2).seal.mix(mixin.seal))

        println(k.toString)
        k match {
            case Mix(Take(Drop(Seal(Map(Seal(Map(Seal(Map(Seal(FromSIterable(array)),f)),g)),h)),0),2),x) => ()
            case _ => fail
        }

        assertEquals(u, k)
        */
        ()
    }
}
