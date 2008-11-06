
package madatest.range


import mada.range._
import junit.framework.Assert._



class FromArrayListTest {
    def testTrivial = {
        assertEquals(FromString("abcdefg"), FromArrayList('a','b','c','d','e','f','g'))
        assertTrue(FromString("abcdefg") != FromArrayList('a','X','c','d','e','f','g'))
    }
}
