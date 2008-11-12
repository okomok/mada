
package madatest.rng


import mada.rng.Window._
import mada.rng.FromArray
import mada.Terminal
import junit.framework.Assert._


class WindowTest {
    def testTrivial {
        assertEquals(FromArray(1,2,3,4), Terminal(FromArray(0,1,2,3,4,5,6)).window(1,5).eval)
        assertEquals(FromArray(2), Terminal(FromArray(0,1,2,3,4,5,6)).window(1,5).window(1,2).eval)
    }
}
