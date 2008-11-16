
package madatest.rng


import mada.NDebug
import mada.rng.Foreach._
import mada.rng.Reverse._
import mada.rng.Conversions._
import mada.rng.ToArrayListExpr
import mada.rng.ArrayList
import junit.framework.Assert._


class ReverseTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).reverse.eval)
    }

    def testPointer() {
        RandomAccessReadablePointerTest(from(Array(6,5,4,3,2,1,0)).reverse.eval.begin, 7, Array(0,1,2,3,4,5,6))
    }
}
