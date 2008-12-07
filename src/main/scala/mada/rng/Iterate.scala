
package mada.rng


object Iterate extends Iterate; trait Iterate extends Predefs {
    final def iterate[A](_1: A, _2: A => A) = IterateExpr(_1, _2).expr
}


case class IterateExpr[A](_1: A, _2: A => A) extends ExprV2.Adapter[Nothing, Rng[A]] {
    override protected def _base = UnfoldRightExpr(_1, { (x: A) => Some(x, _2(x)) })
}
