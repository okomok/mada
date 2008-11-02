
package madatest.range


import mada.range._;


class FromArrayTest extends TestCase {
    def applyTest() {
        testTrivial
        testWritable
        testWritable2
    }

    def testTrivial() = {
        val a = FromArray(Array.range(0, 6))
        val b = FromArray(Array.range(0, 6))
        assertTrue(a == b)
    }

    def testWritable() = {
        val a = Array.range(0, 6);
        a(0) = 99
        val r2 = FromArray(Array.range(0, 6))
        *(r2.begin) = 99
        assertTrue(FromArray(a) == r2)
    }

    def testWritable2() = {
        val a = Array.range(0, 6);
        val r2 = FromArray(Array.range(0, 6))
        val v = *(r2.begin + 2).toLong;
        assertTrue(v == 2)
        (r2.begin + 2).write(97)
        assertTrue((r2.begin + 2).read == 97)
        (r2.begin)(2) = 96
        assertTrue(*(r2.begin + 2) == 96)
    }
}
