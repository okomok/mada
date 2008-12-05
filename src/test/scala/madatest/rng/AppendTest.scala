
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Append._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class AppendTest {
    def testTrivial {
        val actual = from(Array(0,18,14,17)) ++ from(Array(19, 8,13, 6, 4,23, 0,12,15,11, 4))
        detail.TestRandomAccessReadWrite(example1, actual.eval)
    }

    def testNontrivial {
        val actual = from(Array(0,18,14,17)) ++
            from(Array(19, 8,13, 6, 4)) ++ from(empty1) ++
            from(Array(23, 0,12,15)) ++ from(empty1) ++
            from(Array(11, 4)) ++ from(empty1) ++ from(empty1)
        detail.TestRandomAccessReadWrite(example1, actual.eval)
    }

    def testEmpty {
        detail.TestEmpty((from(empty1) ++ from(empty1)).eval)
        detail.TestEmpty((from(empty1) ++ from(empty1) ++ from(empty1)).eval)
    }
}
