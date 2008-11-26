
package mada.rng


object ShallowCopy extends ShallowCopy

trait ShallowCopy extends Predefs {
    class MadaRngShallowCopy[A](_1: Expr[Rng[A]]) {
        def rng_shallowCopy = ShallowCopyExpr(_1).expr
    }
    implicit def toMadaRngShallowCopy[A](_1: Expr[Rng[A]]): MadaRngShallowCopy[A] = new MadaRngShallowCopy[A](_1)
}


case class ShallowCopyExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval = {
        val r = _1.eval
        r.begin.copy <=< r.end.copy
    }
}
