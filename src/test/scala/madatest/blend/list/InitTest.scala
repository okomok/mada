

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class InitTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        type l = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val l: l = 3 :: "hello" :: i :: 'a' :: 12 :: Nil

        val li: l#init = l.init
        val lii: Int :: String :: java.lang.Integer :: Char :: Nil = li
        val A = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(A, lii)
    }

    def testOne: Unit = {
        type l = Int :: Nil
        val l: l = 12 :: Nil

        val li: l#init = l.init
        val lii: Nil = li
        assertSame(Nil, lii)
    }
}
