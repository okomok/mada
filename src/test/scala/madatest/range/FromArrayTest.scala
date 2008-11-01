
package madatest.range


import junit.framework._;
import Assert._;
import mada.range._;


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
        val a = FromArray(Array.range(0, 6))
        val b = FromArray(Array.range(0, 6))
        assertTrue(a->Equals(b))
    }
    def testWritable() = {
        val a = Array.range(0, 6);
        a(0) = 99
        val r2 = FromArray(Array.range(0, 6))
        *(r2.begin) = 99
        assertTrue(FromArray(a)->Equals(r2))
    }
    def testWritable2() = {
        val a = Array.range(0, 6);
        val r2 = FromArray(Array.range(0, 6))
        val v = *(r2.begin + 2).toLong;
        assertTrue(v == 2)
        (r2.begin + 2).write(97)
        assertEquals((r2.begin + 2).read, 97)
        (r2.begin)(2) = 96
        assertEquals(*(r2.begin + 2), 96)
    }
}
