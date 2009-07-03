

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blendtest.listtest


import mada.blend._
import mada.meta


class FindTypeTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testTrivial: Unit = {
        val i = new java.lang.Integer(10)
        val lst1 = 3.1 :: "hello" :: i :: 9 :: 'a' :: 12 :: Nil

        val e1: Option[Int] = lst1.findType[Int]
        assertEquals(9, e1.get)

        val e2 = lst1.findType[java.lang.Integer]
        assertSame(i, e2.get)

        val e3: Option[scala.List[Int]] = lst1.findType[scala.List[Int]]
        assertTrue(e3.isEmpty)
    }

    def testNil: Unit = {
        val lst1 = Nil
        val e3: Option[scala.List[Int]] = lst1.findType[scala.List[Int]]
        assertTrue(e3.isEmpty)
    }
}
