

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.sequencetest


import mada.sequence._
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
}
