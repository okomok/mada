

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class TupleTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testFrom: Unit = {
        val t: (Int, String, java.lang.Integer) = (3, "hello", new java.lang.Integer(10))

        type l = Int :: String :: java.lang.Integer :: Nil
        val l: l = list.fromTuple(t)

        assertSame(t._2, l.nth[_1N])
    }

    def testTo: Unit = {
        type l = Int :: String :: java.lang.Integer :: Nil
        val l: l = 3 :: "hello" :: new java.lang.Integer(10):: Nil

        val t: (Int, String, java.lang.Integer) = l.toTuple

        assertSame(t._2, l.nth[_1N])
    }
}
