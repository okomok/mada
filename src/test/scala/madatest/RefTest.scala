
package madatest


import mada.Ref
import junit.framework.Assert._


class RefTest {
    def assign(v: Ref[Int]) {
        v.deref = 12
    }

    def assign2(v: Ref[Int]) {
        v := 12
    }

    def testTrivial {
        var x = 10
        val v = new Ref(x)
        assign(v)
        x = v.deref
        assertTrue(x == 12)
    }

    def testTrivial2 {
        var x = 10
        val v = new Ref(x)
        assign2(v)
        x = v.deref
        assertTrue(x == 12)
    }
}
