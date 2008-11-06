
package madatest.range


import mada.range._
import junit.framework.Assert._



class FromStringTest extends TestCase {
    def testTrivial = {
        assertTrue(FromString("abcdefg") == FromString("abcdefg"))
        assertTrue(FromString("abcdefg") != FromString("abXdefg"))
        assertTrue(FromString("abcdefg") == FromArray('a','b','c','d','e','f','g'))
        assertTrue(FromString("abcdefg") != FromArray('a','X','c','d','e','f','g'))
    }
}
