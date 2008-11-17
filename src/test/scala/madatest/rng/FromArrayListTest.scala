
package madatest.rng


import mada.rng.ArrayList
import mada.rng.From._
import junit.framework.Assert._


class FromArrayListTest {
    def testTrivial = {
        assertEquals(from("abcdefg").eval, from(ArrayList('a','b','c','d','e','f','g')).eval)
        AssertNotEquals(from("abcdefg").eval, from(ArrayList('a','X','c','d','e','f','g')).eval)
    }
}
