

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest


import com.github.okomok.mada

import mada.string
import junit.framework.Assert._


class StringsTest extends org.scalatest.junit.JUnit3Suite {
    def testConcat: Unit = {
        assertEquals( "abcdef", string.concat("ab", "", "c", "def", "") )
        AssertNotEquals( "abcxef", string.concat("ab", "", "c", "def", "") )

        assertEquals( "ab3cdefg", string.concat("ab", 3, "c", "def", "", 'g') )
    }
}
