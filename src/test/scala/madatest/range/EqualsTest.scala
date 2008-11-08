
package madatest.range


import mada.range._
import junit.framework.Assert._


class EqualsTest {
    def testTrivial = {
        assertEquals(Ints(2, 5), Ints(2, 5))
        AssertNotEquals(Ints(2, 6), Ints(2, 5))
        assertEquals(Ints(2, 5).asRangeIn(SinglePassTraversal), Ints(2, 5))
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testCompile(r1: Range[From1], r2: Range[From2], f: (To1, To2) => Boolean) = {
        r1.equals(r2, f);
    }
}
