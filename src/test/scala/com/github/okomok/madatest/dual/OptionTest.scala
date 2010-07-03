

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada.dual
import dual._
import junit.framework.Assert._


class OptionTest extends junit.framework.TestCase {
    def testTrivial {
        type s = Some[Int]
        val s: s = Some(3)
        val e: s#get = s.get
        val k: Int = e
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

    def testMatch {
        val s = Some(3)
        s match {
            case Some(e) => assertEquals(3, e)
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

    def testOrElse {
        type s = Some[_3N]
        val s: s = Some(_3N)
        val r: s#getOrElse[Int] = s.getOrElse(8)
        val k: _3N = r

        type n = None
        val n: n = None
        val q: n#getOrElse[Int] = n.getOrElse(8)
        val p: Int = q
        assertEquals(8, q)
    }
