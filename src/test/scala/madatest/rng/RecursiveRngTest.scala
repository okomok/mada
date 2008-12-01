
package madatest.rng


import mada.NDebug
import mada.rng._
import mada.rng.Map._
import mada.rng.AsRngBy._
import mada.rng.From._
import junit.framework.Assert._
import detail.Example._


class RecursiveRngTest {
    def testTrivial {
        val x = new RecursiveRng[Int]
        x := x // left-recursion will result in stack-overflow
        val r = x.eval
        val p = r.begin
        // val p1 = p.copy // undefined in left-recursion
        ()
    }

    def testEmpty {
    }
}
