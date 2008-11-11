
package madatest.rng


import mada.rng._
import junit.framework.Assert._


class EqualsTest {
    def testTrivial = {
        assertEquals(Interval(2, 5), Interval(2, 5))
        AssertNotEquals(Interval(2, 6), Interval(2, 5))
        assertEquals(Interval(2, 5).asRngIn(SinglePassTraversal), Interval(2, 5))
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testCompile(r1: Rng[From1], r2: Rng[From2], f: (To1, To2) => Boolean) = {
        r1.equals(r2, f);
    }
}
