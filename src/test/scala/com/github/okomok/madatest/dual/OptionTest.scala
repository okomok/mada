

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._
import nat.peano.Literal._
import nat.Peano


class OptionTest extends junit.framework.TestCase {

    def testTrivial {
        type s = Some[Box[Int]]
        val s: s = Some(Box(3))
        val e: s#get = s.get
        val k: Int = e.unbox
        assertEquals(3, k)

        type n = None
        val n: n = None
        try {
            val e: n#get = n.get
            fail("never come here")
        } catch {
            case _: NoSuchElementException =>
        }
    }

    def testIsEmpty {
        {
            type s = Some[Box[Int]]
            val s: s = Some(Box(3))
            meta.assertSame[`false`, s#isEmpty]
            val e: s#isEmpty = s.isEmpty
            val k: `false` = e
        }
        {
            type s = None
            val s: s = None
            meta.assertSame[`true`, s#isEmpty]
            val e: s#isEmpty = s.isEmpty
            val k: `true` = e
        }
        ()
    }

    def testIsDefined {
        {
            type s = Some[Box[Int]]
            val s: s = Some(Box(3))
            meta.assertSame[`true`, s#isDefined]
            val e: s#isDefined = s.isDefined
            val k: `true` = e
        }
        {
            type s = None
            val s: s = None
            meta.assertSame[`false`, s#isDefined]
            val e: s#isEmpty = s.isEmpty
            val k: `true` = e
        }
        ()
    }

    def testUndual {
        {
            type s = Some[Box[Int]]
            val s: s = Some(Box(3))
            meta.assertSame[scala.Some[Int], s#undual]
            val e: s#undual = s.undual
            assertEquals(scala.Some(3), e)
        }
        {
            type s = None
            val s: s = None
            meta.assertSame[scala.None.type, s#undual]
            val e: s#undual = s.undual
            assertSame(scala.None, s.undual)
        }
        ()
    }


    def testMatch {
        val s = Some(Box(3))
        s match {
            case Some(Box(e)) => assertEquals(3, e)
            case _ => fail("doh")
        }

        val n: Option = None
        n match {
            case Some(e) => fail("doh")
            case None =>
        }
        ()
    }

    class natty {
         def apply[o <: Option{ type get <: Peano }](o: o): apply[o] = o.get.increment
        type apply[o <: Option{ type get <: Peano }] = o#get#increment
    }
    val natty = new natty

    def testNatty {
        type s = Some[_3]
        val s = Some(_3)
        meta.assertSame[_4, natty#apply[s]]
        assert(_4 === natty(s))
    }

    def testGetOrElse {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[_3, s#getOrElse[const0[_8]]]
        val r: s#getOrElse[const0[_8]] = s.getOrElse(const0(_8))
        val k: _3 = r

        type n = None
        val n: n = None
        val q: n#getOrElse[const0[_8]] = n.getOrElse(const0(_8))
        val p: _8 = q
        ()
    }


    case class Plus1() extends Function1 {
        override def self = this
        override type self = Plus1
        override def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat.increment
        override type apply[n <: Any] = n#asInstanceOfNat#increment
    }

    def testMapSome {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[Some[_4], s#map[Plus1]]
        val m: s#map[Plus1] = s.map(Plus1())
        assertEquals(Some(_4), m)
    }

    def testMapNone {
        type s = None
        val s: s = None
        meta.assertSame[None, s#map[Plus1]]
        val m: s#map[Plus1] = s.map(Plus1())
        assertEquals(None, m)
    }


    case class Plus1Get() extends Function1 {
        override def self = this
        override type self = Plus1Get
        override def apply[n <: Any](n: n): apply[n] = Some(n.asInstanceOfNat.increment)
        override type apply[n <: Any] = Some[n#asInstanceOfNat#increment]
    }

    def testFlatMapSome {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[Some[_4], s#flatMap[Plus1Get]]
        val m: s#flatMap[Plus1Get] = s.flatMap(Plus1Get())
        assertEquals(Some(_4), m)
    }

    def testFlatMapNone {
        type s = None
        val s: s = None
        meta.assertSame[None, s#map[Plus1Get]]
        val m: s#flatMap[Plus1Get] = s.flatMap(Plus1Get())
        assertEquals(None, m)
    }


    case class Is2() extends Function1 {
        override def self = this
        override type self = Is2
        override def apply[n <: Any](n: n): apply[n] = n.asInstanceOfNat === _2
        override type apply[n <: Any] = n#asInstanceOfNat# ===[_2]
    }

    def testFilterSome {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[None, s#filter[Is2]]
        val m: s#filter[Is2] = s.filter(Is2())
        assertEquals(None, m)
    }

    def testFilterSome2 {
        type s = Some[_2]
        val s: s = Some(_2)
        meta.assertSame[Some[_2], s#filter[Is2]]
        val m: s#filter[Is2] = s.filter(Is2())
        assertEquals(Some(_2), m)
    }

    def testFilterNone {
        type s = None
        val s: s = None
        meta.assertSame[None, s#filter[Is2]]
        val m: s#filter[Is2] = s.filter(Is2())
        assertEquals(None, m)
    }


    def testExistsSome {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[`false`, s#exists[Is2]]
        val m: s#exists[Is2] = s.exists(Is2())
        assertEquals(`false`, m)
    }

    def testExistsSome2 {
        type s = Some[_2]
        val s: s = Some(_2)
        meta.assertSame[`true`, s#exists[Is2]]
        val m: s#exists[Is2] = s.exists(Is2())
        assertEquals(`true`, m)
    }

    def testExistsNone {
        type s = None
        val s: s = None
        meta.assertSame[`false`, s#exists[Is2]]
        val m: s#exists[Is2] = s.exists(Is2())
        assertEquals(`false`, m)
    }


    case class AddTo(result: java.util.ArrayList[Int]) extends Function1 {
        override def self = this
        override type self = AddTo
        override def apply[n <: Any](n: n): apply[n] = { result.add(n.asInstanceOfNat.undual); Unit }
        override type apply[n <: Any] = Unit
    }

    def testForeachSome {
        type adder = AddTo
        val adder: adder = AddTo(new java.util.ArrayList[Int])
        type s = Some[_2]
        val s: s = Some(_2)
        meta.assertSame[Unit, s#foreach[adder]]
        val m: s#foreach[adder] = s.foreach(adder)
        assertEquals(1, adder.result.size)
        assertEquals(2, adder.result.get(0))
    }

    def testForeachNone {
        type adder = AddTo
        val adder: adder = AddTo(new java.util.ArrayList[Int])
        type s = None
        val s: s = None
        meta.assertSame[Unit, s#foreach[adder]]
        val m: s#foreach[adder] = s.foreach(adder)
        assertTrue(adder.result.isEmpty)
    }


    case class Some4() extends Function0 {
        override def self = this
        override type self = Some4
        override def apply: apply = Some(_4)
        override type apply = Some[_4]
    }

    def testOrElseSome {
        type s = Some[_3]
        val s: s = Some(_3)
        meta.assertSame[Some[_3], s#orElse[Some4]]
        val m: s#orElse[Some4] = s.orElse(Some4())
        assertEquals(Some(_3), m)
    }

    def testOrElseNone {
        type s = None
        val s: s = None
        meta.assertSame[Some[_4], s#orElse[Some4]]
        val m: s#orElse[Some4] = s.orElse(Some4())
        assertEquals(Some(_4), m)
    }

}
