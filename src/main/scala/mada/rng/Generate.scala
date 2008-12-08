
package mada.rng


object Generate extends Generate; trait Generate extends Predefs {
    final def generate[A](_1: Unit => Option[A]) = GenerateExpr(_1).expr
}


case class GenerateExpr[A](_1: Unit => Option[A]) extends Expr.Alias[Nothing, Rng[A]] {
    override protected def _alias = UnfoldRightExpr((), { (x: Unit) => next })

    private def next = _1() match {
        case Some(e) => Some((e, ()))
        case None => None
    }
}
