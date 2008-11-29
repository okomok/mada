
package mada.rng


import Drop._
import Take._


object Slice extends Slice; trait Slice extends Predefs {
    class MadaRngSlice[A](_1: Expr[Rng[A]]) {
        def rng_slice(_2: Long, _3: Long) = SliceExpr(_1, _2, _3).expr
    }
    implicit def toMadaRngSlice[A](_1: Expr[Rng[A]]): MadaRngSlice[A] = new MadaRngSlice[A](_1)
}


case class SliceExpr[A](_1: Expr[Rng[A]], _2: Long, _3: Long) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]) = _1.rng_drop(_2).rng_take(_3 - _2).eval(c)
}
