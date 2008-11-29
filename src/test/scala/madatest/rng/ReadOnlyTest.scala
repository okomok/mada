
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

    def testSymmetric1 {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val r = from(ac).eval
        detail.TestRandomAccessReadOnly(ex, r.begin.readOnly <=< r.end)
    }

    def testSymmetric2 {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val r = from(ac).eval
        detail.TestRandomAccessReadOnly(ex, r.begin <=< r.end.readOnly)
    }

    def testCannotRead {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)

        val thrown =
            try {
                detail.TestRandomAccessReadWrite(ex, from(ac).rng_readOnly.eval); false
            } catch {
                case e: ErrorNotWritable[_] => true
            }

        assertTrue(thrown)
    }
}
