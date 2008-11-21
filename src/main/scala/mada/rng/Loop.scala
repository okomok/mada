
package mada.rng


object Loop extends Loop

trait Loop extends Predefs {
    class MadaRngLoop[A](_1: Expr[Rng[A]]) {
        def rng_loop(_2: A => Boolean) = LoopExpr(_1, _2).expr
    }
    implicit def toMadaRngLoop[A](_1: Expr[Rng[A]]): MadaRngLoop[A] = new MadaRngLoop[A](_1)
}


case class LoopExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Unit] {
    override def _eval = _1 match {
        case FilterExpr(x1, x2) => LoopImpl(x1.eval, { (e: A) => if (x2(e)) _2(e) else true }) // loop-filter fusion
        case MapExpr(x1, x2) => LoopImpl(x1.eval, _2 compose x2) // loop-map fusion
        case _ => LoopImpl(_1.eval, _2)
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
