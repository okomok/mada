


// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.exprv2


import mada._
import junit.framework.Assert._


case class SizeExpr[A](override val _1: Expr.Of[List[A]]) extends Expr.Method[List[A], Int] {
    override protected def _default = _1.eval.size
}


case class MapExpr[A, B](override val _1: Expr.Of[List[A]], _2: A => B) extends Expr.Method[List[A], List[B]] {
    override def _default = _1 match {
        case MapExpr(x1, x2) => { deforsed = true; MapExpr(x1, _2 compose x2).eval }
        case _ => _1.eval.map(_2)
    }

    var deforsed = false
}


case class IteratorToListExpr[A](_1: Expr.Of[Iterator[A]]) extends Expr[Iterator[A], List[A]] {
    override protected def _eval[B](x: Expr[List[A], B]): B = x match {
        case Self => _1.eval(this) // as method
        case Unknown => _1.eval.toList // default-implementation of this method
        case SizeExpr(_) if (hookSize) => 99 // as object
        case MapExpr(x1, x2) => _1.eval.map(x2).toList // as object
        case _ => dontKnow(x)
    }

    var hookSize = true
}

case class IteratorToListExprProxy[A](_1: Expr.Of[Iterator[A]]) extends Expr.Alias[Iterator[A], List[A]] {
    override protected def _alias = IteratorToListExpr(_1)
}


class ExprV2Test {
    def aList = Array(1,2,3,4,5).toList
    def anIterator = aList.elements

    def testConstant: Unit = {
        assertEquals(10, Expr(10).eval)
    }

    def testMethod: Unit = {
        assertEquals(5, SizeExpr(Expr(aList)).eval)
    }

    def testMap: Unit = {
        val lst = MapExpr(MapExpr(Expr(aList), { (e: Int) => "wow" }), { (e: String) => 10 }).eval
        assertEquals(10, lst.first)
    }

    def testNontrivial: Unit = {
        val x = IteratorToListExpr(Expr(anIterator))
        assertEquals(aList, x.eval)
        x.hookSize = true
        assertEquals(99, SizeExpr(x).eval)
        x.hookSize = false
        assertEquals(5, SizeExpr(x).eval)
    }

    def testAlias: Unit = {
        val x = IteratorToListExprProxy(Expr(anIterator))
        assertEquals(aList, x.eval)
        assertEquals(99, SizeExpr(x).eval)
    }

    def testCut: Unit = {
        val x = MapExpr(MapExpr(Expr(aList), { (e: Int) => "wow" }), { (e: String) => 10 })
        x.deforsed = false
        x.eval
        assertTrue(x.deforsed)
        val y = MapExpr(MapExpr(Expr(aList), { (e: Int) => "wow" }).xseal, { (e: String) => 10 })
        y.deforsed = false
        assertFalse(y.deforsed)
    }

    def testLazy: Unit = {
        val l1 = Expr(100).xlazy
        assertSame(l1.eval, l1.eval)
        val l2 = Expr(101).xlazy
        assertSame(l2.eval, l2.eval)
        assertNotSame(l2.eval, l1.eval)
    }
}
