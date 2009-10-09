

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class RemoveTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val _m: l#remove[_1N] = l.remove[_1N]
        val m: Int :: java.lang.Integer :: Char :: Int :: Nil = _m
        val e: Char = m.nth[_2N]
        assertEquals('a', e)
    }
}
