
package mada.rng


object Window extends Window

trait Window extends Predefs {
    class MadaRngWindow[A](_1: Expr[Rng[A]]) {
        def window(_2: Expr[Long], _3: Expr[Long]) = WindowExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngWindow[A](_1: Expr[Rng[A]]) = new MadaRngWindow(_1)
}


case class WindowExpr[A](_1: Expr[Rng[A]], _2: Expr[Long], _3: Expr[Long]) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case WindowExpr(x1, x2, x3) => WindowImpl(x1.eval, x2.eval + _2.eval, x2.eval + _3.eval)
        case _ => WindowImpl(_1.eval, _2.eval, _3.eval)
    }
}


object WindowImpl {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRng", Implies(n < 0, r models BidirectionalTraversal))

        val p = r.begin.advance(n)
        p <=< p.clone.advance(m - n)
    }
}
