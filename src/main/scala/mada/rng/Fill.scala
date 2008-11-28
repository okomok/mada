
package mada.rng


import Foreach._
import Outdirect._
import Pointer._


object Fill extends Fill

trait Fill extends Predefs {
    class MadaRngFill[A](_1: Expr[Rng[A]]) {
        def rng_fill[B <: A](_2: B) = FillExpr(_1, _2).expr
    }
    implicit def toMadaRngFill[A](_1: Expr[Rng[A]]): MadaRngFill[A] = new MadaRngFill[A](_1)
}


case class FillExpr[A, B <: A](_1: Expr[Rng[A]], _2: B) extends Expr[Unit] {
    override def _eval[U](c: Context[Unit, U]) = _1.rng_outdirect.rng_foreach(*(_) = _2).eval(c)
}
