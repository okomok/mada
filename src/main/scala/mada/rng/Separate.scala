
package mada.rng


import Append._
import ReadOnly._


object Separate extends Separate; trait Separate extends Predefs {
    class MadaRngSeparate[A](_1: Expr.Of[Rng[Rng[A]]]) {
        def separate(_2: Expr.Of[Rng[A]]) = SeparateExpr(_1, _2, None).expr
        def separate(_2: Expr.Of[Rng[A]], _3: Traversal) = SeparateExpr(_1, _2, Some(_3)).expr
    }
    implicit def toMadaRngSeparate[A](_1: Expr.Of[Rng[Rng[A]]]): MadaRngSeparate[A] = new MadaRngSeparate[A](_1)
}


case class SeparateExpr[A](_1: Expr.Of[Rng[Rng[A]]], _2: Expr.Of[Rng[A]], _3: Option[Traversal]) extends Expr.Alias[Rng[A], Rng[A]] {
    override protected def _alias = {
        val sep = _2.readOnly.eval
        FlattenExpr(MapExpr(_1, { (r: Rng[A]) => sep./.append(r.toExpr)./ }), _3)
    }
}
