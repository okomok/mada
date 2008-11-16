

package madatest


import mada._
import junit.framework.Assert._


class ExprTest {
    def testTrivial {
    }

    def testLazy {
        val l1 = Expr(100)
        assertSame(l1.eval, l1.eval)
        val l2 = Expr(101)
        assertSame(l2.eval, l2.eval)
        assertNotSame(l2.eval, l1.eval)
    }
}
