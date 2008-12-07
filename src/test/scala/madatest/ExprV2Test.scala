

package madatest.exprv2


import mada._
import junit.framework.Assert._


case class SizeExpr[A](override val _1: ExprV2.Of[List[A]]) extends ExprV2.Method[List[A], Int] {
    override protected def _default = _1.eval.size
}


case class MapExpr[A, B](override val _1: ExprV2.Of[List[A]], _2: A => B) extends ExprV2.Method[List[A], List[B]] {
    override def _default = _1 match {
        case MapExpr(x1, x2) => MapExpr(x1, _2 compose x2).eval
        case _ => _1.eval.map(_2)
    }
}


case class IteratorToListExpr[A](_1: ExprV2.Of[Iterator[A]]) extends ExprV2[Iterator[A], List[A]] {
    override protected def _eval[B](x: ExprV2[List[A], B]): B = x match {
        case Self => _1.eval(this) // as method
        case Default => _1.eval.toList // default-implementation of this method
        case SizeExpr(_) if (hookSize) => 99 // as object
        case MapExpr(x1, x2) => _1.eval.map(x2).toList // as object
        case _ => unknown(x)
    }

    var hookSize = true
}

case class IteratorToListExprProxy[A](_1: ExprV2.Of[Iterator[A]]) extends ExprV2.Alias[Iterator[A], List[A]] {
    override protected def _alias = IteratorToListExpr(_1)
}


class ExprV2Test {
    def aList = Array(1,2,3,4,5).toList
    def anIterator = aList.elements

    def testConstant: Unit = {
        assertEquals(10, ExprV2.Constant(10).eval)
    }

    def testMethod: Unit = {
        assertEquals(5, SizeExpr(ExprV2.Constant(aList)).eval)
    }

    def testMap: Unit = {
        val lst = MapExpr(MapExpr(ExprV2.Constant(aList), { (e: Int) => "wow" }), { (e: String) => 10 }).eval
        assertEquals(10, lst.first)
    }

    def testNontrivial: Unit = {
        val x = IteratorToListExpr(ExprV2.Constant(anIterator))
        assertEquals(aList, x.eval)
        x.hookSize = true
        assertEquals(99, SizeExpr(x).eval)
        x.hookSize = false
        assertEquals(5, SizeExpr(x).eval)
    }

    def testAdapter: Unit = {
        val x = IteratorToListExprProxy(ExprV2.Constant(anIterator))
        assertEquals(aList, x.eval)
        assertEquals(99, SizeExpr(x).eval)
    }

    def testLazy {
        val l1 = ExprV2.Constant(100).xlazy
        assertSame(l1.eval, l1.eval)
        val l2 = ExprV2.Constant(101).xlazy
        assertSame(l2.eval, l2.eval)
        assertNotSame(l2.eval, l1.eval)
    }
}
