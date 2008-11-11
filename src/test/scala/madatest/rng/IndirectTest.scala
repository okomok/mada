
package madatest.rng


import mada.rng._
import junit.framework.Assert._


class IndirectTest {
    def testTrivial {
        import Indirect._
        FromArray(1,2,3).outdirect.indirect
    }
}
