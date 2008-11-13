
package mada.rng


object Forall extends Forall

trait Forall {
    class MadaRngForall[A](_1: Expr[Rng[A]]) {
        def find(_2: A => Boolean) = ForallExpr(_1, _2).expr
    }
    implicit def toMadaRngForall[A](_1: Expr[Rng[A]]) = new MadaRngForall(_1)
}

case class ForallExpr[A](_1: Expr[Rng[A]], _2: A => Boolean)
        extends Expression[Boolean](FindExpr[A](_1, !_2(_)).eval == None)
