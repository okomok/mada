

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package utiltest


import com.github.okomok.mada

import junit.framework.Assert._
import mada.util


class OptionalTest extends org.scalatest.junit.JUnit3Suite {

    def testTrivial = {
        val h = util.optional((3 :: Nil).head)
        assertEquals(Some(3), h)
        val k = util.optional(Nil.head)
        assertEquals(None, k)
    }

}
