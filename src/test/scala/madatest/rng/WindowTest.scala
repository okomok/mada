
package madatest.rng


import mada.NDebug
import mada.rng.Window._
import mada.rng.FromArray
import mada.Expr
import mada.ExprConversions._
import junit.framework.Assert._


class WindowTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(FromArray(1,2,3,4), Expr(FromArray(0,1,2,3,4,5,6)).window(1L,5L).eval)
        assertEquals(FromArray(3,4), Expr(FromArray(0,1,2,3,4,5,6)).window(1L,5L).window(2L,4L).eval)
    }
}
