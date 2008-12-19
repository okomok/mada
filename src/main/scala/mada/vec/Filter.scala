

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Filter extends Filter; trait Filter extends Predefs {
    class MadaVecFilter[A](_1: Expr.Of[Vector[A]]) {
        def filter(_2: A => Boolean) = FilterExpr(_1, _2).expr
    }
    implicit def toMadaVecFilter[A](_1: Expr.Of[Vector[A]]): MadaVecFilter[A] = new MadaVecFilter[A](_1)
}


case class FilterExpr[A](override val _1: Expr.Of[Vector[A]], _2: A => Boolean) extends Expr.Transform[Vector[A]] {
    override protected def _default = _1 match {
        case FilterExpr(x1, x2) => FilterExpr(x1, {(e: A) => x2(e) && _2(e)}).eval // filter-filter fusion
        case _ => FilterImpl(_1.eval, _2)
    }
}


object FilterImpl {
    import Force._
    import Window._
    import stl.Remove._

    def apply[A](* : Vector[A], pred: A => Boolean): Vector[A] = {
        // This seems better than filterCopyIf into ArrayList.
        val v = *./.force./
        v./.window(0, v./.stl_removeIf(!pred(_: A))./)./
    }
}
