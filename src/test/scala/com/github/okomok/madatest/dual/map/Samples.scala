

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package dualtest; package maptest


import com.github.okomok.mada

import mada.dual._
import nat.peano.Literal._
import junit.framework.Assert._


object Samples {
    type o = ordering.ofNatPeano
    val o: o = ordering.ofNatPeano

    type m1 =    map.single[_1, box[scala.Int], o]
    val m1: m1 = map.single(_1, box(1), o)

    type m4 =    map.single[_4, box[scala.Char], o]
    val m4: m4 = map.single(_4, box('4'), o)

    type m7 =    map.single[_7, box[String], o]
    val m7: m7 = map.single(_7, box("seven"), o)

    type m6 =    map.Node[_3, _6, box[scala.Double], m4, m7, o]
    val m6: m6 = map.Node(_3, _6, box(3.4f), m4, m7, o)

    type m3 =    map.Node[_5, _3, box[scala.Boolean], m1, m6, o]
    val m3: m3 = map.Node(_5, _3, box(true), m1, m6, o)

    type m13 =    map.single[_13, box[scala.Char], o]
    val m13: m13 = map.single(_13, box('k'), o)

    type m14 =     map.Node[_2, _14, box[scala.Int], m13, map.Nil[o], o]
    val m14: m14 = map.Node(_2, _14, box(14), m13, map.Nil(o), o)

    type m10 =     map.Node[_3, _10, box[String], map.Nil[o], m14, o]
    val m10: m10 = map.Node(_3, _10, box("10"), map.Nil(o), m14, o)

    type m8 =    map.Node[_9, _8, box[scala.Boolean], m3, m10, o]
    val m8: m8 = map.Node(_9, _8, box(false), m3, m10, o)
}

class SamplesTest extends junit.framework.TestCase {
    def testTrivial {
        AssertInvariant(Samples.m8)
    }
}
