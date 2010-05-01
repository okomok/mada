

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package blendtest; package listtest


import com.github.okomok.mada

import mada.blend._
import mada.meta


class ConstraintTest {
    import junit.framework.Assert._
    assertFalse(scala.Nil eq Nil)

    def inc[l <: List { type head <: Int }](_l: l): Int = _l.head + 2

    def testTrivial: Unit = {
        val lst1 = 1 :: "hello" :: 'a' :: 12 :: Nil
        assertEquals(3, inc(lst1))
    }
}
