

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import junit.framework.Assert._
import junit.framework.AssertionFailedError


class AssertImpliesTest extends org.scalatest.junit.JUnit3Suite {
    def testThis {
        AssertImplies(true, true)
        AssertImplies(false, true)
        AssertImplies(false, false)

        var thrown =
            try {
                AssertImplies(true, false); false
            } catch {
                case _: AssertionFailedError => true
            }
        assertTrue(thrown)
    }
}
