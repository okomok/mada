
package madatest.rng


import mada.rng._
import mada.rng.AsRngBy._
import mada.rng.Conversions._
import junit.framework.Assert._


class EqualsTest {
    def testTrivial = {
        assertEquals(from(2, 5).eval, from(2, 5).eval)
        assertEquals(from(2L, 5L).eval, from(2L, 5L).eval)
        AssertNotEquals(from(2, 6).eval, from(2, 5).eval)
        assertEquals(from(2, 5).asRngBy(SinglePassTraversal).eval, from(2, 5).eval)
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testCompile(r1: Rng[From1], r2: Rng[From2], f: (To1, To2) => Boolean) = {
        r1.equals(r2, f);
    }
}
