

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
        val lst = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        val a1: Box[Int] = lst.nth(_0N)
        assertEquals(3, a1.unbox)
        val a2: Box[String] = lst.nth(_1N)
        assertEquals("hello", a2.unbox)
        val a3: Box[java.lang.Integer] = lst.nth(_2N)
        assertSame(i, a3.unbox)
        val a4: Box[Char] = lst.nth(_3N)
        assertEquals('a', a4.unbox)
        assertEquals(10, lst.nth(_2N).unbox.intValue)
    }

    def testSize {
        val i = new java.lang.Integer(10)
        val lst = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        assert(_4N === lst.size)
        assert(Nil.size === _0N)
    }
    def testTypeErase {
        val i = new java.lang.Integer(10)
        val lst = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        assertEquals("[]", Nil.toString)
        assertEquals("[3, hello, 10, a]", lst.toString)
    }

    def testEquals {
        val i = new java.lang.Integer(10)
        val j = new java.lang.Integer(10)
        assertEquals(i, j)
        val lst1 = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        val lst2 = Box(3) :: Box("hello") :: Box(j) :: Box('a') :: Nil
        val lst3 = Box(3) :: Box("hello") :: Box('b') :: Nil
        val lst4 = Box(2) :: Box("hello") :: Box(i) :: Box('a') :: Nil
        val lst5 = Nil
        assertEquals(lst1, lst2)
        AssertNotEquals(lst1, lst3)
        AssertNotEquals(lst1, lst4)
        assertEquals(lst5, lst5)
        AssertNotEquals(lst1, lst5)
    }

    def testDrop {
        val i = new java.lang.Integer(10)
        val lst = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        val a = Box(i) :: Box('a') :: Box(12) :: Nil
        val s = lst.drop(_0N)
        val b: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = lst.drop(_2N)
        val c = lst.drop(_5N)
//        val d = lst.drop(_9N)
        assertEquals(a, b)
//        assertEquals(0, d.size)
//        assertEquals(Nil, d)
        assertEquals(Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil, s)
        assertEquals(Box(i) :: Box('a') :: Box(12) :: Nil, b)
        assertEquals(Nil, c)
    }

    def testTake {
        val i = new java.lang.Integer(10)
        val lst = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        val a = lst.take(_0N)
        val b: Box[Int] :: Box[String] :: Nil = lst.take(_2N)
        val c = lst.take(_5N)
        assertEquals(Nil, a)
        assertEquals(Box(3) :: Box("hello") :: Nil, b)
        assertEquals(Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil, c)
    }

    def testIsEmpty {
        assert(Nil.isEmpty)
        assertNot((Box(3) :: Box("hello") :: Nil).isEmpty)
    }
/*
    def testTyped {
        import mada.sequence
        val i = new java.lang.Integer(10)
        val el: sequence.List[Any] = 3 :: "hello" :: i :: 'a' :: sequence.Nil.of[Any]
        val tl = 3 :: "hello" :: i :: 'a' :: Nil
        assertEquals(tl, list.typed[Int :: String :: java.lang.Integer :: Char :: Nil](el))
        assertEquals(Nil, list.typed[Nil](sequence.Nil))
        ()
    }
*/
    def testPrepend {
        val i = new java.lang.Integer(10)
        val lst1 = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        val lst2 = Box("wow") :: Box(99) :: Nil
        assertEquals(Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Box("wow") :: Box(99) :: Nil, lst1 ::: lst2)
        assertEquals(Nil ::: Nil, Nil)
        assertEquals(lst1 ::: Nil, Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil)
        assertEquals(Nil ::: lst1, Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil)
        val k: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = (Box(3) :: Box("hello") :: Nil) ::: (Box(i) :: Nil) ::: (Box('a') :: Box(12) :: Nil) ::: Nil
        assertEquals(lst1, k)
    }


    def testReversePrepend {
        val i = new java.lang.Integer(10)
        type Lst1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        type Lst2 = Box[String] :: Box[Int] :: Nil
        val lst1: Lst1 = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        val lst2: Lst2 = Box("wow") :: Box(99) :: Nil

        val lst12: Lst1 reverse_::: Lst2 = lst1 reverse_::: lst2
        assertEquals(Box(12) :: Box('a') :: Box(i) :: Box("hello") :: Box(3) :: Box("wow") :: Box(99) :: Nil, lst12)
        assertEquals(Nil reverse_::: Nil, Nil)
        assertEquals(lst1 reverse_::: Nil, Box(12) :: Box('a') :: Box(i) :: Box("hello") :: Box(3)  :: Nil)
        assertEquals(Nil reverse_::: lst1, Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil)
        val k: Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil = (Box("hello") :: Box(3) :: Nil) reverse_::: (Box(i) :: Nil) reverse_::: (Box(12) :: Box('a') :: Nil) reverse_::: Nil
        assertEquals(lst1, k)
        ()
    }

    def testReverse {
        val i = new java.lang.Integer(10)
        type Lst1 = Box[Int] :: Box[String] :: Box[java.lang.Integer] :: Box[Char] :: Box[Int] :: Nil
        val lst1: Lst1 = Box(3) :: Box("hello") :: Box(i) :: Box('a') :: Box(12) :: Nil
        val lst1r: Lst1#reverse = lst1.reverse
        val lst1r_ : Box[Int] :: Box[Char] :: Box[java.lang.Integer] :: Box[String] :: Box[Int] :: Nil = lst1r
        assertEquals(Box(12) :: Box('a') :: Box(i) :: Box("hello") :: Box(3) :: Nil, lst1r)
        assertEquals(Nil.reverse, Nil)
    }
}


object ListTezt {
    import meta.{ assert, assertSame }

    trait testAt {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Nil
        assertSame[lst#nth[_0N], Box[Int]]
        assertSame[lst#nth[_1N], Box[String]]
        assertSame[lst#nth[_2N], Box[Double]]
        assertSame[lst#nth[_3N], Box[Char]]
        assertSame[lst#nth[_2N# +[_1N]], Box[Char]]
    }

    trait testSize {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Nil
        assert[lst#size# ===[_4N]]
        assert[Nil#size# ===[_0N]]
    }

    trait testIsEmpty {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Nil
        assertSame[Nil#isEmpty, `true`]
        assertSame[lst#isEmpty, `false`]
    }

    trait testDrop {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil
        assertSame[Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil, lst#drop[_0N]]
        assertSame[Box[Double] :: Box[Char] :: Box[Float] :: Nil, lst#drop[_2N]]
        assertSame[Nil, lst#drop[_5N]]
    }

    trait testTake {
        type lst = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil
        assertSame[Nil, lst#take[_0N]]
        assertSame[Box[Int] :: Box[String] :: Nil, lst#take[_2N]]
        assertSame[Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil, lst#take[_5N]]
    }

    trait testPrepend {
        type lst1 = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil
        type lst2 = Box[Boolean] :: Box[Byte] :: Nil
        assertSame[Nil, Nil#prepend[Nil]]
        assertSame[Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Box[Boolean] :: Box[Byte] :: Nil, lst2#prepend[lst1]]
        assertSame[lst1, lst1#prepend[Nil]]
        assertSame[lst1, Nil#prepend[lst1]]

        assertSame[Nil, Nil ::: Nil]
        assertSame[Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Box[Boolean] :: Box[Byte] :: Nil, lst1 ::: lst2]
        assertSame[lst1, lst1 ::: Nil]
        assertSame[lst1, Nil ::: lst1]
        assertSame[lst1, (Box[Int] :: Box[String] :: Nil) ::: (Box[Double] :: Nil) ::: (Box[Char] :: Box[Float] :: Nil) ::: Nil]
    }

    type prependprepend[l1 <: List, l2 <: List, r <: List] = r#prepend[l1]#prepend[l2]

    trait testPrepend2 {
        type lst1 = Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil
        type lst2 = Box[Boolean] :: Box[Byte] :: Nil
        type lst3 = Box[Char] :: Box[String] :: Nil
        type r = prependprepend[lst2, lst3, lst1]
        assertSame[Box[Char] :: Box[String] :: Box[Boolean] :: Box[Byte] :: Box[Int] :: Box[String] :: Box[Double] :: Box[Char] :: Box[Float] :: Nil, r]
    }
}
