
package mada.rng


object Loop extends Loop

trait Loop extends Predefs {
    class MadaRngLoop[A](_1: Expr[Rng[A]]) {
        def foreach(_2: Expr[A => Boolean]) = LoopExpr(_1, _2).expr
    }
    implicit def toMadaRngLoop[A](_1: Expr[Rng[A]]): MadaRngLoop[A] = new MadaRngLoop[A](_1)
}


case class LoopExpr[A](_1: Expr[Rng[A]], _2: Expr[A => Boolean]) extends Expr[Unit] {
    override def _eval = _1 match {
        case FilterExpr(x1, x2) => { // loop-filter fusion
            val a2 = _2.eval
            val b2 = x2.eval
            LoopImpl(x1.eval, { (e: A) => if (b2(e)) a2(e) else true })
        }
        case MapExpr(x1, x2) => LoopImpl(x1.eval, _2.eval compose x2.eval) // loop-map fusion
        case _ => LoopImpl(_1.eval, _2.eval)
    }
}


object LoopImpl {
    def apply[A](r: Rng[A], f: A => Boolean) {
        val (p, q) = (r.begin, r.end)
        while (p != q && f(*(p)))
            ++(p)
        // r is abandoned for fusions.
    }
}
