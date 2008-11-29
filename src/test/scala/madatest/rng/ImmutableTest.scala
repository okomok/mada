
package madatest.rng


import mada.rng._
import mada.rng.From._
import junit.framework.Assert._


class ImmutableTest {
    def testTrivial {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val r = from(ac).eval
        detail.TestRandomAccess(ex, r.begin.readOnly <=< r.end)
    }

    def testCannotMute {
        val ex = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)
        val ac = Array(0,18,14,17,19, 8,13, 6, 4,23, 0,12,15,11, 4)

        val thrown =
            try {
                detail.TestRandomAccessReadOnly(ex, r.begin.immutable <=< r.end); false
            } catch {
                case e: ErrorNotMutable[_] => true
            }

        assertTrue(thrown)
    }
}
