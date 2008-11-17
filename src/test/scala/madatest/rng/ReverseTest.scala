
package madatest.rng


import mada.NDebug
import mada.rng.Foreach._
import mada.rng.Reverse._
import mada.rng.From._
import mada.rng.ToArrayListExpr
import mada.rng.ArrayList
import junit.framework.Assert._


// expected same:<[6, 5, 4, 3, 2, 1]> was not:<[6, 5, 4, 3, 2, 1]>


class ReverseTest {
    def testTrivial {
        NDebug.value = false
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).reverse.eval)
        assertEquals(from(Array(6,5,4,3,2,1,0)).eval, from(Array(0,1,2,3,4,5,6)).reverse.reverse.reverse.eval)
    }

    def testFusion() {
        NDebug.value = false
        val l = from(Array(6,5,4,3,2,1)).toLazy
        val l1 = l.eval
        val l2 = l.reverse.reverse.eval
        assertSame(l1, l2)
    }

    def testPointer() {
        RandomAccessReadablePointerTest(from(Array(6,5,4,3,2,1,0)).reverse.eval.begin, 7, Array(0,1,2,3,4,5,6))
    }
}
