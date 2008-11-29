
package mada.rng


import Find._


object Forall extends Forall; trait Forall extends Predefs {
    class MadaRngForall[A](_1: Expr[Rng[A]]) {
        def rng_forall(_2: A => Boolean) = ForallExpr(_1, _2).expr
    }
    implicit def toMadaRngForall[A](_1: Expr[Rng[A]]): MadaRngForall[A] = new MadaRngForall[A](_1)
}


case class ForallExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Boolean] {
    override def _eval = _1.eval(ForallContext(_2))
}

case class ForallContext[A](_2: A => Boolean) extends Context[Rng[A], Boolean] {
    override def apply(_1: Expr[Rng[A]]) = _1.rng_find(!_2(_: A)).eval == None
}
