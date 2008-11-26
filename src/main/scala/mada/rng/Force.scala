
package mada.rng


import ArrayListToRng._
import ToArrayList._


object Force extends Force

trait Force extends Predefs {
    class MadaRngForce[A](_1: Expr[Rng[A]]) {
        def rng_force = ForceExpr(_1).expr
    }
    implicit def toMadaRngForce[A](_1: Expr[Rng[A]]): MadaRngForce[A] = new MadaRngForce[A](_1)
}


case class ForceExpr[A](_1: Expr[Rng[A]]) extends Expr[Rng[A]] {
    override def _eval[U](c: Context[Rng[A], U]) = _1.rng_toArrayList.toRng.eval(c)
}
