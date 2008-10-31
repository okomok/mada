
package madatest.range


import junit.framework._;
import Assert._;
import mada.range


object FromArrayTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[FromArrayTest]);
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite);
    }
}

class FromArrayTest extends TestCase("FromArray") {
    def testTrivial() = {
        assertTrue(false);
    }
}
