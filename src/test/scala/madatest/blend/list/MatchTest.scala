

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blendtest.listtest


import mada.blend._
import mada.meta
import mada.meta.nat.Literal._


class MatchTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        type l = Int :: String :: java.lang.Integer :: Nil
        val l: l = 3 :: "hello" :: new java.lang.Integer(10):: Nil

        l match {
            case x :: y :: z :: Nil => {
                assertEquals(3, x + 1 - 1)
                assertEquals("hello", y)
            }
            case _ => fail("doh")
        }

        ()
    }

    def testNil: Unit = {
        Nil match {
            case Nil => ()
            case _ => fail("doh")
        }
        ()
    }
}
