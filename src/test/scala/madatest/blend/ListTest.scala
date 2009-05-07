

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Blend._
import mada.Meta


class ListUnmetaTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testAt = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        val _0: Int = lst.at[Meta._0I]
        assertEquals(3, _0)
        val _1: String = lst.at[Meta._1I]
        assertEquals("hello", _1)
        val _2: java.lang.Integer = lst.at[Meta._2I]
        assertSame(i, _2)
        val _3: Char = lst.at[Meta._3I]
        assertEquals('a', _3)
        assertEquals(10, lst.at[Meta._2I].intValue)
    }

    def testLength = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(4, lst.length)
        assertEquals(0, Nil.length)
    }

    def testTypeErase = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals("List()", Nil.toString)
        assertEquals("List(3, hello, 10, a)", lst.toString)
    }

    def testEquals = {
        val i = new java.lang.Integer(10)
        val j = new java.lang.Integer(10)
        assertEquals(i, j)
        val lst1 = 3 :: "hello" :: i :: 'a' :: Nil
        val lst2 = 3 :: "hello" :: j :: 'a' :: Nil
        val lst3 = 3 :: "hello" :: 'b' :: Nil
        val lst4 = 2 :: "hello" :: i :: 'a' :: Nil
        val lst5 = Nil
        assertEquals(lst1, lst2)
        AssertNotEquals(lst1, lst3)
        AssertNotEquals(lst1, lst4)
        assertEquals(lst5, lst5)
        AssertNotEquals(lst1, lst5)
    }
}

class ListMetaTest {
    import Meta.{ assert, assertSame }

    trait testAt {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#at[Meta._0I], Int]
        assertSame[lst#at[Meta._1I], String]
        assertSame[lst#at[Meta._2I], Double]
        assertSame[lst#at[Meta._3I], Char]
        assertSame[lst#at[Meta._2I#add[Meta._1I]], Char]
    }

    trait testLength {
        type lst = Int :: String :: Double :: Char :: Nil
        assert[lst#length#equals[Meta._4I]]
    }
}
