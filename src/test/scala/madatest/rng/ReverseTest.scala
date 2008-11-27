
package madatest.rng


import mada.NDebug
import mada.rng.Foreach._
import mada.rng.Reverse._
import mada.rng.From._
import mada.rng.ToArrayListExpr
import mada.rng.ArrayList
import junit.framework.Assert._


class ReverseTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).rng_reverse.eval)
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).rng_reverse.rng_reverse.rng_reverse.eval)
    }

    def testFusion() {
        NDebug.value = false
        val l = from(Array(6,5,4,3,2,1)).toLazy
        val l1 = l.eval
        val l2 = l.rng_reverse.rng_reverse.eval
        assertSame(l1, l2)
    }

    def testPointer() {
        detail.NDebugReverse.value = false
        detail.TestRandomAccessReadablePointer(from(Array(6,5,4,3,2,1,0)).rng_reverse.eval.begin, 7, Array(0,1,2,3,4,5,6))
    }
}
