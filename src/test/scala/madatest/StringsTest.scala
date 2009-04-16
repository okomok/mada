

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest


import mada.Strings
import junit.framework.Assert._


class StringsTest {
    def testConcat: Unit = {
        assertEquals( "abcdef", Strings.concat("ab", "", "c", "def", "") )
        AssertNotEquals( "abcxef", Strings.concat("ab", "", "c", "def", "") )

        assertEquals( "ab3cdefg", Strings.concat("ab", 3, "c", "def", "", 'g') )
    }
}
