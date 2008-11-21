
package madatest.rng


import mada.rng.Indirect._
import mada.rng.Outdirect._
import mada.rng.From._
import mada.rng.ArrayCompatible._
import junit.framework.Assert._


class IndirectTest {
    def testTrivial {
        from(Array(1,2,3)).rng_outdirect.rng_indirect
    }
}
