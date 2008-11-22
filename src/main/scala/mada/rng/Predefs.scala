
package mada.rng


object Predefs extends Predefs

trait Predefs extends Traits
        with DefaultCompatibles {
    implicit def toMadaRngExpr[A](r: Rng[A]): Expr[Rng[A]] = Expr(r)
}
