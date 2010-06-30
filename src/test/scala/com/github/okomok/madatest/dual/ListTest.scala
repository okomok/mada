

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada

import mada.dual._


class ListTest extends junit.framework.TestCase {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def testAt = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        val a1: Int = lst.nth(_0N)
        assertEquals(3, a1)
        val a2: String = lst.nth(_1N)
        assertEquals("hello", a2)
        val a3: java.lang.Integer = lst.nth(_2N)
        assertSame(i, a3)
        val a4: Char = lst.nth(_3N)
        assertEquals('a', a4)
        assertEquals(10, lst.nth(_2N).intValue)
    }

    def testSize: Unit = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        assert(_4N === lst.size)
        assert(Nil.size === _0N)
    }
    def testTypeErase: Unit = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals("[]", Nil.toString)
        assertEquals("[3, hello, 10, a]", lst.toString)
    }

    def testEquals: Unit = {
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

    def testDrop: Unit = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val a = i :: 'a' :: 12 :: Nil
        val s = lst.drop(_0N)
        val b: java.lang.Integer :: Char :: Int :: Nil = lst.drop(_2N)
        val c = lst.drop(_5N)
//        val d = lst.drop(_9N)
        assertEquals(a, b)
//        assertEquals(0, d.size)
//        assertEquals(Nil, d)
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: Nil, s)
        assertEquals(i :: 'a' :: 12 :: Nil, b)
        assertEquals(Nil, c)
    }

    def testTake: Unit = {
        val i = new java.lang.Integer(10)
        val lst = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val a = lst.take(_0N)
        val b: Int :: String :: Nil = lst.take(_2N)
        val c = lst.take(_5N)
        assertEquals(Nil, a)
        assertEquals(3 :: "hello" :: Nil, b)
        assertEquals(3 :: "hello" :: i :: 'a' :: 12 :: Nil, c)
    }

    def testIsEmpty: Unit = {
        assert(Nil.isEmpty)
        assertNot((3 :: "hello" :: Nil).isEmpty)
    }

    def testTyped: Unit = {
        import mada.sequence
        val i = new java.lang.Integer(10)
        val el: sequence.List[Any] = 3 :: "hello" :: i :: 'a' :: sequence.Nil.of[Any]
        val tl = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(tl, list.typed[Int :: String :: java.lang.Integer :: Char :: Nil](el))
        assertEquals(Nil, list.typed[Nil](sequence.Nil))
        ()
    }

    def testPrepend: Unit = {
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


    def testReversePrepend: Unit = {
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
        ()
    }

    def testReverse: Unit = {
        val i = new java.lang.Integer(10)
        type Lst1 = Int :: String :: java.lang.Integer :: Char :: Int :: Nil
        val lst1: Lst1 = 3 :: "hello" :: i :: 'a' :: 12 :: Nil
        val lst1r: Lst1#reverse = lst1.reverse
        val lst1r_ : Int :: Char :: java.lang.Integer :: String :: Int :: Nil = lst1r
        assertEquals(12 :: 'a' :: i :: "hello" :: 3 :: Nil, lst1r)
        assertEquals(Nil.reverse, Nil)
    }
}


object ListTezt {
    import meta.{ assert, assertSame }

    trait testAt {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[lst#nth[_0N], Int]
        assertSame[lst#nth[_1N], String]
        assertSame[lst#nth[_2N], Double]
        assertSame[lst#nth[_3N], Char]
        assertSame[lst#nth[_2N# +[_1N]], Char]
    }

    trait testSize {
        type lst = Int :: String :: Double :: Char :: Nil
        assert[lst#size# ===[_4N]]
        assert[Nil#size# ===[_0N]]
    }

    trait testIsEmpty {
        type lst = Int :: String :: Double :: Char :: Nil
        assertSame[Nil#isEmpty, `true`]
        assertSame[lst#isEmpty, `false`]
    }

    trait testDrop {
        type lst = Int :: String :: Double :: Char :: Float :: Nil
        assertSame[Int :: String :: Double :: Char :: Float :: Nil, lst#drop[_0N]]
        assertSame[Double :: Char :: Float :: Nil, lst#drop[_2N]]
        assertSame[Nil, lst#drop[_5N]]
    }

    trait testTake {
        type lst = Int :: String :: Double :: Char :: Float :: Nil
        assertSame[Nil, lst#take[_0N]]
        assertSame[Int :: String :: Nil, lst#take[_2N]]
        assertSame[Int :: String :: Double :: Char :: Float :: Nil, lst#take[_5N]]
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

    type prependprepend[l1 <: List, l2 <: List, r <: List] = r#prepend[l1]#prepend[l2]

    trait testPrepend2 {
        type lst1 = Int :: String :: Double :: Char :: Float :: Nil
        type lst2 = Boolean :: Byte :: Nil
        type lst3 = Char :: String :: Nil
        type r = prependprepend[lst2, lst3, lst1]
        assertSame[Char :: String :: Boolean :: Byte :: Int :: String :: Double :: Char :: Float :: Nil, r]
    }
}
