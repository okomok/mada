
package mada.rng


object Forall extends Forall

trait Forall extends Predefs {
    class MadaRngForall[A](_1: Expr[Rng[A]]) {
        def rng_forall(_2: A => Boolean) = ForallExpr(_1, _2).expr
    }
    implicit def toMadaRngForall[A](_1: Expr[Rng[A]]): MadaRngForall[A] = new MadaRngForall[A](_1)
}


case class ForallExpr[A](_1: Expr[Rng[A]], _2: A => Boolean) extends Expr[Boolean] {
    override def _eval = {
        val not2: A => Boolean = !_2(_)
        FindExpr(_1, not2).eval == None
    }
}
