
package madatest


import mada.Ref
import junit.framework.Assert._


class RefTest {
    def assign(v: Ref[Int]) {
        v.deref = 12
    }

    def testTrivial {
        var x = 10
        val v = Ref(x)
        assign(v)
        x = v.deref
        assertTrue(x == 12)
    }
}
