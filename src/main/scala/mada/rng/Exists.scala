
package mada.rng


import Find._


object Exists extends Exists; trait Exists extends Predefs {
    class MadaRngExists[A](_1: ExprV2.Of[Rng[A]]) {
        def exists(_2: A => Boolean) = ExistsExpr(_1, _2).expr
    }
    implicit def toMadaRngExists[A](_1: ExprV2.Of[Rng[A]]): MadaRngExists[A] = new MadaRngExists[A](_1)
}


case class ExistsExpr[A](override val _1: ExprV2.Of[Rng[A]], _2: A => Boolean) extends ExprV2.Method[Rng[A], Boolean] {
    override def _default = _1.find(_2).eval != None
}
