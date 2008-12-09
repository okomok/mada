
package mada.rng


import DropWhile._


object Span extends Span; trait Span extends Predefs {
    class MadaRngSpan[A](_1: Expr.Of[Rng[A]]) {
        def span(_2: A => Boolean) = SpanExpr(_1, _2).expr
    }
    implicit def toMadaRngSpan[A](_1: Expr.Of[Rng[A]]): MadaRngSpan[A] = new MadaRngSpan[A](_1)
}


case class SpanExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], (Rng[A], Rng[A])] {
    override protected def _default = SpanImpl(_1.eval, _2)
}


object SpanImpl {
    def apply[A](r: Rng[A], f: A => Boolean): (Rng[A], Rng[A]) = {
        AssertModels(r, ForwardTraversal)
        val r2 = r./.dropWhile(f)./
        (r.begin <=< r2.end, r2)
    }
}
