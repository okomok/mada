

package madatest


import mada._
import junit.framework.Assert._


class ExprTest {
    def testTrivial {
    }

    case class IntExpr(_1: Int) extends Expr[Int] {
        override def _eval = _1
    }

    def testCut {
        val x = IntExpr(100)
        x match {
            case IntExpr(i) => ()
        }
        x.cut match {
            case IntExpr(i) => fail("doh")
            case _ => ()
        }
    }

    def testLazy {
        val l1 = Expr(100)
        assertSame(l1.eval, l1.eval)
        val l2 = Expr(101)
        assertSame(l2.eval, l2.eval)
        assertNotSame(l2.eval, l1.eval)
    }

    def testSugar {
        assertEquals(Expr(100)!, 100)
    }
}
