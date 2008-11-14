
package madatest.rng


import mada.rng._
import mada.rng.Conversions._
import junit.framework.Assert._


class IndirectTest {
    def testTrivial {
        import Indirect._
        from(Array(1,2,3)).eval.outdirect.indirect
    }
}
