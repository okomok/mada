
package madatest.rng


import mada.rng._
import mada.rng.From._
import mada.rng.ReadOnly._
import junit.framework.Assert._


class ReadOnlyTest {
    def testTrivial {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        detail.TestRandomAccessReadOnly(ex, from(ac).rng_readOnly.eval)
    }

    def testCantWrite {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)

        val thrown =
            try {
                detail.TestRandomAccessReadWrite(ex, from(ac).rng_readOnly.eval); false
            } catch {
                case e: NotWritablePointerError[_] => true
            }

        assertTrue(thrown)
    }
}
