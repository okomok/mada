

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import junit.framework.Assert._

import mada.peg.Compatibles._
import mada.peg._


class SwitchTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        val g = switch("z" inCase 'e', "gh" inCase 'f' , "abc" inCase 'q')
        assertTrue("RR" >> g >> "LL"  matches "RRezLL")
        assertTrue("RR" >> g >> "LL"  matches "RRfghLL")
        assertTrue("RR" >> g >> "LL"  matches "RRqabcLL")
    }
}
