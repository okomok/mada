

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.blend


import mada.Blend._
import mada.Meta
import mada.Meta.IntLiterals._


class ListUnmetaTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testAt = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        val _0: Int = lst.at[_0I]
        assertEquals(3, _0)
        val _1: String = lst.at[_1I]
        assertEquals("hello", _1)
        val _2: java.lang.Integer = lst.at[_2I]
        assertSame(i, _2)
        val _3: Char = lst.at[_3I]
        assertEquals('a', _3)
        assertEquals(10, lst.at[_2I].intValue)
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

    def testDrop = {
//        val k = Nil.drop[_2I]
//        assertEquals(Nil, k)

        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val a = i :: 'a' :: 12 :: Nil
        val s = lst.drop[_0I]
        val b = lst.drop[_2I]
        val c = lst.drop[_5I]
        val d = lst.drop[_9I]
        assertEquals(3, b.length)
        assertEquals(a, b)
        assertEquals(0, c.length)
        assertEquals(Nil, c)
        assertEquals(0, d.length)
        assertEquals(Nil, d)
    }

    def testTyped = {
        val i = new java.lang.Integer(10)
        val el: scala.List[Any] = scala.::[Any](3, scala.::[Any]("hello", scala.::[Any](i, scala.::[Any]('a', scala.Nil))))
        val tl = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(tl, List.typed[ Int :: String :: java.lang.Integer :: Char :: Nil](el))

        assertEquals(Nil, List.typed[Nil](scala.Nil))
        ()
    }
}

class ListMetaTest {
    import Meta.{ assert, assertSame }

    trait testAt {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#at[_0I], Int]
        assertSame[lst#at[_1I], String]
        assertSame[lst#at[_2I], Double]
        assertSame[lst#at[_3I], Char]
        assertSame[lst#at[_2I#add[_1I]], Char]
    }

    trait testLength {
        type lst = Int :: String :: Double :: Char :: Nil
        assert[lst#length#equals[_4I]]
    }

    trait testDrop {
        type lst = Int :: String :: Double :: Char :: Nil
        type lst1 = lst#drop[_2I]
        assertSame[lst#drop[_4I], Nil]
        assertSame[lst#drop[_10I], Nil]
        assertSame[lst1#length, _2I]
        assertSame[lst1#at[_0I], Double]
        assertSame[lst1#at[_1I], Char]
    }
}
