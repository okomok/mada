

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


class RemoveTest extends junit.framework.TestCase {

    def testTrivial {
        type o = nat.ord
        val o: o = nat.ord

        type m = map.sorted[o]#put[_3, box[Int]]#put[_5, box[Char]]#put[_1, box[String]]
        val m: m = map.sorted(o).put(_3, box(3)).put(_5, box('c')).put(_1, box("wow"))
        AssertInvariant(m)

        type rm = m#remove[_5]
        val rm: rm = m.remove(_5)
        meta.assertSame[_2, rm#size]
        meta.assertSame[None, rm#get[_5]]
        AssertInvariant(rm)
        ()
    }

    def testTrivial1 {
        type m = Samples.m8
        val m: m = Samples.m8

        type rm = m#remove[_1]
        val rm: rm = m.remove(_1)
        meta.assertSame[_8, rm#size]
        meta.assertSame[None, rm#get[_1]]
        AssertInvariant(rm)
        ()
    }

    def testTrivial3 {
        type m = Samples.m8
        val m: m = Samples.m8

        type rm = m#remove[_3]
        val rm: rm = m.remove(_3)
        meta.assertSame[_8, rm#size]
        meta.assertSame[None, rm#get[_3]]
        AssertInvariant(rm)
        //println(rm)
        ()
    }

    def testTrivial8 {
        type m = Samples.m8
        val m: m = Samples.m8

        type rm = m#remove[_8]
        val rm: rm = m.remove(_8)
        meta.assertSame[_8, rm#size]
        meta.assertSame[None, rm#get[_8]]
        AssertInvariant(rm)
        ()
    }

    def testTrivial14 {
        type m = Samples.m8
        val m: m = Samples.m8

        type rm = m#remove[_14]
        val rm: rm = m.remove(_14)
        meta.assertSame[_8, rm#size]
        meta.assertSame[None, rm#get[_14]]
        AssertInvariant(rm)
        ()
    }

    def testTrivial6 {
        type m = Samples.m8
        val m: m = Samples.m8

        type rm = m#remove[_6]
        val rm: rm = m.remove(_6)
        meta.assertSame[_8, rm#size]
        meta.assertSame[None, rm#get[_6]]
        AssertInvariant(rm)
        ()
    }

}
