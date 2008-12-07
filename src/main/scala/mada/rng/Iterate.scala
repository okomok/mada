
package mada.rng


object Iterate extends Iterate; trait Iterate extends Predefs {
    final def iterate[A](_1: A, _2: A => A) = IterateExpr(_1, _2).expr
}


case class IterateExpr[A](_1: A, _2: A => A) extends Expr.Alias[Nothing, Rng[A]] {
    override protected def _alias = UnfoldRightExpr(_1, { (x: A) => Some(x, _2(x)) })
}
