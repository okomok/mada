
package madatest.range


import mada.range._


class EqualsTest extends TestCase {
    def applyTest = {
        assertTrue("0", Numbers(2, 2)->Equals(Numbers(2, 2)))
        assertTrue("1", Numbers(2, 2)->Equals(Numbers(5, 5)))
        assertTrue("2", Numbers(2, 5)->Equals(Numbers(2, 5)))
        assertTrue(Numbers(2, 5)->Equals(Numbers(2, 7)) == false)
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testInference(r1: Range[From1], r2: Range[From2], f: (To1, To2) => Boolean) = {
        r1->Equals(r2);
        r1->EqualsIf(r2, f);
    }
}
