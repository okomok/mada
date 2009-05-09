

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
        val _0: Int = lst.at[_0]
        assertEquals(3, _0)
        val _1: String = lst.at[_1]
        assertEquals("hello", _1)
        val _2: java.lang.Integer = lst.at[_2]
        assertSame(i, _2)
        val _3: Char = lst.at[_3]
        assertEquals('a', _3)
        assertEquals(10, lst.at[_2].intValue)
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
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val a = i :: 'a' :: 12 :: Nil
        val s = lst.drop[_0]
        val b: java.lang.Integer :: Char :: Int :: Nil = lst.drop[_2]
        val c = lst.drop[_5]
//        val d = lst.drop[_9]
        assertEquals(a, b)
//        assertEquals(0, d.length)
//        assertEquals(Nil, d)
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: Nil, s)
        assertEquals(i :: 'a' :: 12 :: Nil, b)
        assertEquals(Nil, c)
    }

    def testTake = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val a = lst.take[_0]
        val b: Int :: String :: Nil = lst.take[_2]
        val c = lst.take[_5]
        assertEquals(Nil, a)
        assertEquals(3 :: "hello" :: Nil, b)
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: Nil, c)
    }

    def testIsEmpty = {
        assertTrue(Nil.isEmpty)
        assertFalse((3 :: "hello" :: Nil).isEmpty)
    }

    def testTyped = {
        val i = new java.lang.Integer(10)
        val el: scala.List[Any] = scala.::[Any](3, scala.::[Any]("hello", scala.::[Any](i, scala.::[Any]('a', scala.Nil))))
        val tl = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(tl, List.typed[ Int :: String :: java.lang.Integer :: Char :: Nil](el))
        assertEquals(Nil, List.typed[Nil](scala.Nil))
        ()
    }

    def testPrepend = {
        val i = new java.lang.Integer(10)
        val lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val lst2 = "wow" :: 99 :: Nil
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: "wow" :: 99 :: Nil, lst1 ::: lst2)
        assertEquals(Nil ::: Nil, Nil)
        assertEquals(lst1 ::: Nil, 3 :: "hello" :: i :: 'a' :: 12 :: Nil)
        assertEquals(Nil ::: lst1, 3 :: "hello" :: i :: 'a' :: 12 :: Nil)
        val k: Int :: String :: java.lang.Integer :: Char :: Int :: Nil = (3 :: "hello" :: Nil) ::: (i :: Nil) ::: ('a' :: 12 :: Nil) ::: Nil
        assertEquals(lst1, k)
    }

    def testReversePrepend = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        type Lst2 = String :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val lst2: Lst2 = "wow" :: 99 :: Nil
        val lst12: Lst1 reverse_::: Lst2 = lst1 reverse_::: lst2
        assertEquals(12 :: 'a' :: i :: "hello" :: 3 :: "wow" :: 99 :: Nil, lst12)
        assertEquals(Nil reverse_::: Nil, Nil)
        assertEquals(lst1 reverse_::: Nil, 12 :: 'a' :: i :: "hello" :: 3  :: Nil)
        assertEquals(Nil reverse_::: lst1, 3 :: "hello" :: i :: 'a' :: 12 :: Nil)
        val k: Int :: String :: java.lang.Integer :: Char :: Int :: Nil = ("hello" :: 3 :: Nil) reverse_::: (i :: Nil) reverse_::: (12 :: 'a' :: Nil) reverse_::: Nil
        assertEquals(lst1, k)
    }

    def testReverse = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val lst1r: Lst1#reverse = lst1.reverse
        val lst1r_ : Int :: Char :: java.lang.Integer :: String :: Int :: Nil = lst1r
        assertEquals(12 :: 'a' :: i :: "hello" :: 3 :: Nil, lst1r)
        assertEquals(Nil.reverse, Nil)
    }

    def testLast = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val e: Lst1#last = lst1.last
        val e_ : Int = e
        assertEquals(12, e_)

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil
        val e2: Lst2#last = lst2.last
        val e2_ : String = e2
        assertEquals("hello", e2_)
    }

    def testSlice = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil;
        {
            val e: Lst1#slice[_0, _2] = lst1.slice[_0, _2]
            val e_ : Int::String::Nil = e
            assertEquals(3::"hello"::Nil, e_)
        }
        {
            val e: Lst1#slice[_1, _5] = lst1.slice[_1, _5]
            val e_ : String :: java.lang.Integer :: Char :: Int :: Nil = e
            assertEquals("hello" :: i :: 'a' :: 12 :: Nil, e_)
        }
        {
            val e: Lst1#slice[_2, _4] = lst1.slice[_2, _4]
            val e_ : java.lang.Integer :: Char :: Nil = e
            assertEquals(i :: 'a' :: Nil, e_)
        }

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil;
        {
            val e: Lst2#slice[_0, _0] = lst2.slice[_0, _0]
            val e_ : Nil = e
            assertEquals(Nil, e_)
        }
        {
            val e: Lst2#slice[_0, _1] = lst2.slice[_0, _1]
            val e_ : String :: Nil = e
            assertEquals("hello" :: Nil, e_)
        }

        type Lst3 = Nil
        val lst3: Lst3 = Nil;
        {
            val e: Lst2#slice[_0, _0] = lst2.slice[_0, _0]
            val e_ : Nil = e
            assertEquals(Nil, e_)
        }
        ()
    }
/*
    def testInit = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val e: Lst1#init = lst1.init
        val e_ : Int :: String :: java.lang.Integer :: Char :: Nil = e
        assertEquals(i, e.at[_2])

        type Lst2 = String :: Nil
        val lst2: Lst2 = "hello" :: Nil
        val e2: Lst2#init = lst2.init
        val e2_ : Nil = e2
        assertEquals(Nil, e2_)
    }
*/
}

class ListMetaTest {
    import Meta.{ assert, assertSame }

    trait testAt {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#at[_0], Int]
        assertSame[lst#at[_1], String]
        assertSame[lst#at[_2], Double]
        assertSame[lst#at[_3], Char]
        assertSame[lst#at[_2#add[_1]], Char]
    }

    trait testLength {
        type lst = Int :: String :: Double :: Char :: Nil
        assert[lst#length#equals[_4]]
        assert[Nil#length#equals[_0]]
    }

    trait testIsEmpty {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[Nil#isEmpty, Meta.`true`]
        assertSame[lst#isEmpty, Meta.`false`]
    }

    trait testDrop {
        type lst = Int :: String :: Double :: Char :: Float :: Nil
        assertSame[Int :: String :: Double :: Char :: Float :: Nil, lst#drop[_0]]
        assertSame[Double :: Char :: Float :: Nil, lst#drop[_2]]
        assertSame[Nil, lst#drop[_5]]
    }

    trait testTake {
        type lst = Int :: String :: Double :: Char :: Float :: Nil
        assertSame[Nil, lst#take[_0]]
        assertSame[Int :: String :: Nil, lst#take[_2]]
        assertSame[Int :: String :: Double :: Char :: Float :: Nil, lst#take[_5]]
    }

    trait testPrepend {
        type lst1 = Int :: String :: Double :: Char :: Float :: Nil
        type lst2 = Boolean :: Byte :: Nil
        assertSame[Nil, Nil#prepend[Nil]]
        assertSame[Int :: String :: Double :: Char :: Float :: Boolean :: Byte :: Nil, lst2#prepend[lst1]]
        assertSame[lst1, lst1#prepend[Nil]]
        assertSame[lst1, Nil#prepend[lst1]]

        assertSame[Nil, Nil ::: Nil]
        assertSame[Int :: String :: Double :: Char :: Float :: Boolean :: Byte :: Nil, lst1 ::: lst2]
        assertSame[lst1, lst1 ::: Nil]
        assertSame[lst1, Nil ::: lst1]

        assertSame[lst1, (Int :: String :: Nil) ::: (Double :: Nil) ::: (Char :: Float :: Nil) ::: Nil]
    }
}
