
package madatest.rng


import mada.rng.From._
import mada.rng.StringCompatible._
import junit.framework.Assert._


class FromStringTest {
    def testTrivial = {
        assertEquals(from("abcdefg").eval, from("abcdefg").eval)
        AssertNotEquals(from("abcdefg").eval, from("abXdefg").eval)
        assertEquals(from("abcdefg").eval, from(Array('a','b','c','d','e','f','g')).eval)
        AssertNotEquals(from("abcdefg").eval, from(Array('a','X','c','d','e','f','g')).eval)
    }

    def testPointer() {
        detail.TestRandomAccessReadablePointer(from("abcde").eval.begin, 5, Array('a','b','c','d','e'))
    }
}
