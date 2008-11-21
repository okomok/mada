
package madatest.rng


import mada.rng._
import mada.rng.ArrayCompatible._
import mada.rng.From._
import mada.rng.ToArray._
import junit.framework.Assert._

class FromArrayTest {
    def testTrivial() = {
        val a = from(Array.range(0, 6)).eval
        val b = from(Array.range(0, 6)).eval
        assertEquals(a, b)
    }

    def testWritable() = {
        val a = Array.range(0, 6)
        a(0) = 99
        val r2 = from(Array.range(0, 6)).eval
        *(r2.begin) = 99
        assertEquals(from(a).eval, r2)
    }

    def testWritable2() = {
        val a = Array.range(0, 6);
        val r2 = from(Array.range(0, 6)).eval
        val v = *(r2.begin + 2);
        assertEquals(v, 2)
        (r2.begin + 2).write(97)
        assertEquals((r2.begin + 2).read, 97)
        (r2.begin)(2) = 96
        assertEquals(*(r2.begin + 2), 96)
    }

    def testMake {
        assertEquals(from(1, 7).eval, from(Array(1,2,3,4,5,6)).eval)
    }

    def testPointer() {
        RandomAccessReadablePointerTest(from(Array(7,6,2,5,1,3,9)).eval.begin, 7, Array(7,6,2,5,1,3,9))
    }

    def testImplicit {
        from(Array('1','2','3')).rng_toArray.eval.deepMkString("abc")
        from(Array('1','2','3')).deepMkString("abc")
    }
}
