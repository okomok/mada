
package madatest.rng


import mada.NDebug
import mada.rng.Window._
import mada.rng.FromArray
import mada.Terminal
import junit.framework.Assert._


class WindowTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(FromArray(1,2,3,4), Terminal(FromArray(0,1,2,3,4,5,6)).window(1,5).eval)
        assertEquals(FromArray(3,4), Terminal(FromArray(0,1,2,3,4,5,6)).window(1,5).window(2,4).eval)
    }
}
