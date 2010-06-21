

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest


import com.github.okomok.mada

import mada.meta
import mada.blend._
import junit.framework.Assert._


class IsSameTest extends junit.framework.TestCase {

    trait Foo[A]

    def testTrivial {
        assertTrue(isSame[Double, Double])
        assertTrue(isSame[Int, Int])
        assertFalse(isSame[Int, String])
        assertFalse(isSame[String, Float])
    }

    def testNoErasure {
        assertTrue(isSame[Foo[Int], Foo[Int]])
        assertFalse(isSame[Foo[Char], Foo[String]])
    }

}
