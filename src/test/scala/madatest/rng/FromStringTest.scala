
package madatest.rng


import mada.rng.Conversions._
import junit.framework.Assert._


class FromStringTest {
    def testTrivial = {
        assertEquals(from("abcdefg").eval, from("abcdefg").eval)
        AssertNotEquals(from("abcdefg").eval, from("abXdefg").eval)
        assertEquals(from("abcdefg").eval, from(Array('a','b','c','d','e','f','g')).eval)
        AssertNotEquals(from("abcdefg").eval, from(Array('a','X','c','d','e','f','g')).eval)
    }
}

