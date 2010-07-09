

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
        meta.assertSame[_3, s#getOrElse[always0[_8]]]
        val r: s#getOrElse[always0[_8]] = s.getOrElse(always0(_8))
        val k: _3 = r

        type n = None
        val n: n = None
        val q: n#getOrElse[always0[_8]] = n.getOrElse(always0(_8))
        val p: _8 = q
        ()
    }

}
