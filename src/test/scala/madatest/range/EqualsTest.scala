
package madatest.range


import junit.framework._
import Assert._
import mada.range._


object EqualsTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[EqualsTest])
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite)
    }
}

class EqualsTest extends TestCase("Equals") {
    def testNumbers() = {
        assertTrue(Numbers(2, 5)->Equals(Numbers(2, 5), new EqualTo[Long, Long]))
    }

    trait To1
    trait From1 extends To1
    trait To2
    trait From2 extends To2

    def testInference(r1: Range[From1], r2: Range[From2], f: (To1, To2) => Boolean) = {
        r1->Equals(r2, f);
    }
}
