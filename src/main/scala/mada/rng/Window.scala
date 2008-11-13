
package mada.rng


object Window extends Window

trait Window {
    class MadaRngWindow[A](_1: Expr[Rng[A]]) {
        def window(_2: Long, _3: Long) = WindowExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngWindow[A](_1: Expr[Rng[A]]) = new MadaRngWindow(_1)
}


case class WindowExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    def eval = _1 match {
        case WindowExpr(a1, a2, a3) => WindowImpl(a1.eval, a2 + _2, a2 + _3)
        case _ => WindowImpl(_1.eval, _2, _3)
    }
}


object WindowImpl {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        Assert("requires ForwardRng", r models ForwardTraversal)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRng", Implies(n < 0, r models BidirectionalTraversal))

        val p = r.begin.advance(n)
        p <=< p.clone.advance(m - n)
    }
}
