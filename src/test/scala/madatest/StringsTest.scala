

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.string
import junit.framework.Assert._


class StringsTest {
    def testConcat: Unit = {
        assertEquals( "abcdef", string.concat("ab", "", "c", "def", "") )
        AssertNotEquals( "abcxef", string.concat("ab", "", "c", "def", "") )

        assertEquals( "ab3cdefg", string.concat("ab", 3, "c", "def", "", 'g') )
    }
}
