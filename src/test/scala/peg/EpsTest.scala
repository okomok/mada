

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package pegtest


import com.github.okomok.mada

import mada.peg._
import junit.framework.Assert._


class EpsTest extends org.scalatest.junit.JUnit3Suite {
    def testTrivial: Unit = {
        assertTrue(eps[Int].matches(mada.sequence.vector.empty.of[Int]))
        assertFalse(eps[Int].matches(mada.sequence.vector.Of(1,2,3)))
    }

    def testCompile(v: mada.sequence.Vector[Char]): Unit = {
        (from("abcd") >> eps).parse(v, 0, 10)
    }
}
