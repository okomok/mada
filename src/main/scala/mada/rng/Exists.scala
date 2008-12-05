
package mada.rng


import Find._


object Exists extends Exists; trait Exists extends Predefs {
    class MadaRngExists[A](_1: Expr[Rng[A]]) {
        def exists(_2: A => Boolean) = ExistsExpr(_1, _2).expr
    }
    implicit def toMadaRngExists[A](_1: Expr[Rng[A]]): MadaRngExists[A] = new MadaRngExists[A](_1)
}


case class ExistsExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Boolean] {
    override def _eval = _1.eval(ExistsContext(_2))
}

case class ExistsContext[A](_2: A => Boolean) extends Context[Rng[A], Boolean] {
    override def apply(_1: Expr[Rng[A]]) = _1.find(_2).eval != None
}
