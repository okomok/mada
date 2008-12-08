
package mada.rng


import Pointer._


object Loop extends Loop; trait Loop extends Predefs {
    class MadaRngLoop[A](_1: Expr.Of[Rng[A]]) {
        def loop(_2: A => Boolean) = LoopExpr(_1, _2).expr
    }
    implicit def toMadaRngLoop[A](_1: Expr.Of[Rng[A]]): MadaRngLoop[A] = new MadaRngLoop[A](_1)
}


case class LoopExpr[A](override val _1: Expr.Of[Rng[A]], _2: A => Boolean) extends Expr.Method[Rng[A], Unit] {
    override protected def _default = _1 match {
        case FilterExpr(x1, x2) => LoopExpr(x1, { (e: A) => if (x2(e)) _2(e) else true }).eval // loop-filter fusion
        case MapExpr(x1, x2) => LoopExpr(x1, _2 compose x2).eval // loop-map fusion
        case _ => LoopImpl(_1.eval, _2)
    }
}


object LoopImpl {
    def apply[A](r: Rng[A], f: A => Boolean) {
        val (p, q) = r.toPair
        while (p != q && f(*(p))) // f(p) would disable loop-map fusion.
            ++(p)
        // r is abandoned for fusions.
    }
}
