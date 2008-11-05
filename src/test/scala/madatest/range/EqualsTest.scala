
package madatest.range


import mada.range._


class EqualsTest extends TestCase {
    def applyTest = {
        assertTrue(Numbers(2, 5) == Numbers(2, 5))
        assertTrue(Numbers(2, 6) != Numbers(2, 5))
        assertTrue(Numbers(2, 5).asRangeIn(SinglePassTraversal) == Numbers(2, 5))
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testInference(r1: Range[From1], r2: Range[From2], f: (To1, To2) => Boolean) = {
        r1.equals(r2, f);
    }
}
