
package madatest


import mada.Ref


class RefTest extends TestCase {
    def assign(v: Ref[Int]) {
        v.deref = 12
    }

    def applyTest {
        var x = 10
        val v = Ref(x)
        assign(v)
        x = v.deref
        assertTrue(x == 12)
    }
}
