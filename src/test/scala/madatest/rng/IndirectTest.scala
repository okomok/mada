
package madatest.rng


import mada.rng._
import mada.rng.Indirect._
import mada.rng.Outdirect._
import mada.rng.Conversions._
import junit.framework.Assert._


class IndirectTest {
    def testTrivial {
        from(Array(1,2,3)).outdirect.indirect
    }
}
