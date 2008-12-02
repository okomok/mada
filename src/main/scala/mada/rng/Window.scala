
package mada.rng


import Implies._


object Window extends Window; trait Window extends Predefs {
    class MadaRngWindow[A](_1: Expr[Rng[A]]) {
        def rng_window(_2: Long, _3: Long) = WindowExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngWindow[A](_1: Expr[Rng[A]]): MadaRngWindow[A] = new MadaRngWindow[A](_1)
}


case class WindowExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    override def _eval = _1 match {
        case WindowExpr(x1, x2, x3) => WindowExpr(x1, x2 + _2, x2 + _3).eval // window-window fusion
        case _ => WindowImpl(_1.eval, _2, _3)
    }
}


object WindowImpl {
    def apply[A](r: Rng[A], n: Long, m: Long): Rng[A] = {
        AssertModels(r, ForwardTraversal)
        Assert("requires n <= m", n <= m)
        Assert("requires BidirectionalRng", (n < 0) implies (r models BidirectionalTraversal))

        val p = r.begin.advance(n)
        p <=< p.copy.advance(m - n)
    }
}
