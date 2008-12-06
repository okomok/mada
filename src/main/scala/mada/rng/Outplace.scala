
package mada.rng


import Force._
import Outdirect._


object Outplace extends Outplace; trait Outplace extends Predefs {
    class MadaRngOutplace[A](_1: Expr[Rng[A]]) {
        def outplace = OutplaceExpr(_1).expr
    }
    implicit def toMadaRngOutplace[A](_1: Expr[Rng[A]]): MadaRngOutplace[A] = new MadaRngOutplace[A](_1)
}


case class OutplaceExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[Pointer[A]]] {
    override def _eval[U](c: Context[Rng[Pointer[A]], U]): U = _1.outdirect.force.eval(c)
}
