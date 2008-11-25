
package madatest.rng


import mada.rng.From._
import mada.rng.ArrayList
import mada.rng.Fill._
import junit.framework.Assert._


class FillTest {
    def testTrivial = {
        val a = ArrayList(1,2,3,4,5,6)
        from(a).rng_fill(9).eval
        assertEquals(from(ArrayList(9,9,9,9,9,9)).eval, from(a).eval)
    }
}
