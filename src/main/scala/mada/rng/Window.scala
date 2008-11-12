
package mada.rng


object Window extends Window

trait Window {
    class MadaRngWindow[A](base: Expr[Rng[A]]) {
        def window(from: Long, until: Long) = WindowExpr(base, from, until)
    }
    implicit def toMadaRngWindow[A](base: Expr[Rng[A]]) = new MadaRngWindow(base)

    class MadaRngWindow2[A](base: WindowExpr[A]) {
        def window(from: Long, until: Long) = WindowExpr(base.base, base.from + from, base.from + until)
    }
    implicit def toMadaRngWindow2[A](base: WindowExpr[A]) = new MadaRngWindow2(base)
}


case class WindowExpr[A](base: Expr[Rng[A]], from: Long, until: Long) extends Expr[Rng[A]] {
    def eval = WindowImpl(base.eval, from, until)
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
