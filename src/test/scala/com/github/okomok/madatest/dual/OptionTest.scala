

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._


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
            meta.assertSame[scala.Option[scala.Any], s#undual]
            val e: s#undual = s.undual
            assertEquals(scala.Some(3), e)
        }
        {
            type s = None
            val s: s = None
            meta.assertSame[scala.Option[scala.Any], s#undual]
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
         def apply[o <: Option{ type get <: Nat }](o: o): apply[o] = o.get.increment
        type apply[o <: Option{ type get <: Nat }] = o#get#increment
    }
    val natty = new natty

    def testNatty {
        type s = Some[_3N]
        val s = Some(_3N)
        meta.assertSame[_4N, natty#apply[s]]
        assert(_4N === natty(s))
    }

    def testGetOrElse {
        type s = Some[_3N]
        val s: s = Some(_3N)
        meta.assertSame[_3N, s#getOrElse[Always0[_8N]]]
        val r: s#getOrElse[Always0[_8N]] = s.getOrElse(new Always0(_8N))
        val k: _3N = r

        type n = None
        val n: n = None
        val q: n#getOrElse[Always0[_8N]] = n.getOrElse(new Always0(_8N))
        val p: _8N = q
        ()
    }

}
