
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Lazy._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class LazyTest {
    def testTrivial {
        detail.TestRandomAccessReadOnly(example1, from(example1).lazy_.eval)
    }
}
